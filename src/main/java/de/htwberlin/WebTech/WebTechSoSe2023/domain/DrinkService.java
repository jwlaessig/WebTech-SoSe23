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

@Service
public class DrinkService {

    @Autowired
    private IDrinkRepo repo;

    public Drink speichern(Drink drink){
        DrinkService drinkService = new DrinkService();
        drink.setAlc(drink.getAlcGehalt(), drink.getMl());
        drink.setGetrunken();
        drink.setAlcWirkt(drink.getGetrunken());
        drink.setNuechtern(drinkService.berechneNuechtern(drink));
        drink.build();
        return repo.save(drink);
    }

    public Drink get(Long id){
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Drink> getAll() {
        return repo.findAll();
    }

    public LocalDateTime berechneNuechtern(Drink drink) {
        final BigDecimal alcAbbauRateProStunde = BigDecimal.valueOf(15.0);
        final BigDecimal alcAbbauRateProMinute = alcAbbauRateProStunde.divide(BigDecimal.valueOf(60.0));

        DrinkService drinkService = new DrinkService();

        int hours = 0;
        int minutes = 0;

    //Die Fehlermeldung besagt, dass es bei der Ausführung des Codes zu einem Fehler kam, der
    //durch einen Aufruf einer Methode auf einem Null-Objekt verursacht wurde. Konkret wird
    //versucht, die Methode "getAll()" auf dem Objekt "drinkService" aufzurufen, aber dieses
    //Objekt ist null und hat somit keine gültige Referenz auf eine Instanz der Klasse
    //"DrinkService".

        //List<Drink> datenbank = drinkService.getAll();
        //Comparator<Drink> drinkComparator = Comparator.comparing(Drink::getNuechtern);
        //Drink newestDrink = Collections.max(datenbank, drinkComparator);
        //LocalDateTime zeit = newestDrink.getNuechtern();

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

            //if (zeit.compareTo(drink.getGetrunken()) < 0) {
                return x.plusHours(hours).plusMinutes(minutes);
            //}
            //else {
            //    Duration duration=Duration.between(drink.getGetrunken(), zeit);
            //    return x.plusHours(hours).plusMinutes(minutes).plus(duration);
            //}
    }
}