package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class DrinkService {

    @Autowired
    private IDrinkRepo repo;

    public Drink save(Drink zuSpeichern){
        Drink drink = new Drink();
        drink.setName(zuSpeichern.getName());
        drink.setAlcGehalt(zuSpeichern.getAlcGehalt());
        drink.setMl(zuSpeichern.getMl());
        drink.setAlc(drink.getAlcGehalt(), drink.getMl());
        drink.setGetrunken();
        drink.setAlcWirkt(drink.getGetrunken());
        drink.setNuechtern(drink.getAlcWirkt());
        drink.build();
        return repo.save(drink);
    }

    public Drink get(Long id){
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Drink> getAll() {
        return repo.findAll();
    }

    public void setNuechtern(LocalDateTime sN, IDrink aktuell) {
        final BigDecimal alcAbbauRateProStunde = BigDecimal.valueOf(15.0);
        final BigDecimal alcAbbauRateProMinute = alcAbbauRateProStunde.divide(BigDecimal.valueOf(60.0));

        int hours = 0;
        int minutes = 0;
        aktuell.setAlc(aktuell.getAlcGehalt(), aktuell.getMl());
        aktuell.build(aktuell.getName(), aktuell.getAlc(), aktuell.getMl(), aktuell.getGetrunken(), aktuell.getAlcWirkt());

        List<Drink> datenbank = getAll();
        Comparator<Drink> drinkComparator = Comparator.comparing(Drink::getNuechtern);
        Drink newestDrink = Collections.max(datenbank, drinkComparator);
        LocalDateTime zeit = newestDrink.getNuechtern();

        BigDecimal ausnuechtern = aktuell.getAlc();

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

        if (zeit.compareTo(aktuell.getGetrunken()) < 0) {
            aktuell.setNuechtern(setNuechtern()); = sN.plusHours(hours).plusMinutes(minutes);
        }
        else{
            Duration duration=Duration.between(aktuell.getGetrunken(), zeit);
            this.nuechtern = sN.plusHours(hours).plusMinutes(minutes).plus(duration);
        }
}