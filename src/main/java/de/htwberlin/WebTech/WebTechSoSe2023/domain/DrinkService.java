package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DrinkService {

    public DrinkService(IDrinkRepo repo){
        this.repo = repo;
    }

    private IDrinkRepo repo;

    private List<Drink> datenbank = new ArrayList<Drink>();

    public Drink speichern(Drink drink){
        drink.setAlc(drink.getAlcGehalt(), drink.getMl());
        drink.setGetrunken();
        drink.setAlcWirkt(this.berechneAlcWirkt(drink.getGetrunken()));
        drink.setNuechtern(this.berechneNuechtern(drink));
        drink.build();
        return repo.save(drink);
    }

    public Drink get(Long id){
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Drink> getAll() {
        return (List<Drink>) repo.findAll();
    }

    public  LocalDateTime berechneAlcWirkt(LocalDateTime bAW){
        return bAW.plusHours(1);
    }

    public LocalDateTime berechneNuechtern(Drink drink) {
        final BigDecimal alcAbbauRateProStunde = BigDecimal.valueOf(15.0);
        final BigDecimal alcAbbauRateProMinute = alcAbbauRateProStunde.divide(BigDecimal.valueOf(60.0));

        int hours = 0;
        int minutes = 0;
        LocalDateTime zeit;

        setDatenbank((List<Drink>) repo.findAll());
        if (!getDatenbank().isEmpty()){
            Comparator<Drink> drinkComparator = Comparator.comparing(Drink::getNuechtern);
            Drink newestDrink = Collections.max(this.getDatenbank(), drinkComparator);
            zeit = newestDrink.getNuechtern();
        } else zeit = drink.getGetrunken();

        BigDecimal ausnuechtern = drink.getAlc();

        while (ausnuechtern.compareTo(alcAbbauRateProStunde) >= 0 || ausnuechtern.compareTo(alcAbbauRateProMinute) >= 0) {
            if (ausnuechtern.compareTo(alcAbbauRateProStunde) >= 0) {
                ausnuechtern = ausnuechtern.subtract(alcAbbauRateProStunde);
                hours = hours + 1;
            } else {
                ausnuechtern = ausnuechtern.subtract(alcAbbauRateProMinute);
                minutes = minutes + 1;
            }
            if (ausnuechtern.compareTo(BigDecimal.ZERO) < 0) {
                break;
            }
        }

        LocalDateTime x = drink.getAlcWirkt();

                if (zeit.compareTo(drink.getGetrunken()) <= 0) {
                    return x.plusHours(hours).plusMinutes(minutes);
                } else {
                    Duration duration = Duration.between(drink.getGetrunken(), zeit);
                    return x.plusHours(hours).plusMinutes(minutes).plus(duration);
                }
    }

    public String canDrive(){
        LocalDateTime zeit;
        LocalDateTime current_time = LocalDateTime.now();

        setDatenbank((List<Drink>) repo.findAll());
        if (!getDatenbank().isEmpty()){
            Comparator<Drink> drinkComparator = Comparator.comparing(Drink::getNuechtern);
            Drink newestDrink = Collections.max(this.getDatenbank(), drinkComparator);
            zeit = newestDrink.getNuechtern();
        } else zeit = current_time;

        if (zeit.isAfter(current_time))
            return "darf nicht Auto fahren";
        else
            return "darf Auto fahren";
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void deleteById(Long id){
        repo.deleteById(id);
    }

    public void setDatenbank(List<Drink> datenbank){
        this.datenbank = datenbank;
    }

    public List<Drink> getDatenbank() { return datenbank;}

}