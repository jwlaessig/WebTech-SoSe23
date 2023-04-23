package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class DrinkBuilder {

    private String name;
    private BigDecimal alc; // in ml
    // die durchschnittliche Alkoholabbaurate des Körpers pro Stunde
    // beträgt 0,5l Bier innerhalb einer Stunde - Quelle chatGPT
    private Integer ml;
    private LocalDateTime getrunken;
    private LocalDateTime alcWirkt;
    private LocalDateTime nuechtern;

    private BigDecimal alcAbbauRateProStunde = BigDecimal.valueOf(25);
    private BigDecimal alcAbbauRateProMinute = BigDecimal.valueOf(25/60);

    public DrinkBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DrinkBuilder setAlc(String alc) {
        this.alc = new BigDecimal(alc);
        return this;
    }

    public DrinkBuilder setMl(String ml) {
        this.ml = new Integer(ml);
        return this;
    }

    public DrinkBuilder setGetrunken() {
        this.getrunken = LocalDateTime.now();
        return this;
    }

    public DrinkBuilder setAlcWirkt() {
        this.alcWirkt = LocalDateTime.now().plusHours(1);
        return this;
    }

    // hier muss noch eingebaut werden, dass der wert von nuechtern der
    // vorherigen trinkeinlage eingearbeitet werden muss.
    // dazu ist aber die Datenbank nötig.
    public DrinkBuilder setNuechtern() {
        int hours = 0;
        int minutes = 0;

        BigDecimal ausnuechtern = alc;

        while (ausnuechtern.compareTo(alcAbbauRateProStunde) >= 0) {
            ausnuechtern = ausnuechtern.subtract(alcAbbauRateProStunde);
            hours = hours + 1;
        }

        while (ausnuechtern.compareTo(alcAbbauRateProMinute) >= 0) {
            ausnuechtern = ausnuechtern.subtract(alcAbbauRateProMinute);
            minutes = minutes + 1;
        }

        this.nuechtern = alcWirkt.plusHours(hours).plusMinutes(minutes);
        return this;
    }


    public Drink build(Drink.Category cat) {
        return switch (cat) {
            case BIER -> new Bier(name, alc, ml, getrunken, alcWirkt, nuechtern);
            case SCHNAPS -> new Schnaps(name, alc, ml, getrunken, alcWirkt, nuechtern);
            case LIKOER -> new Likoer(name, alc, ml, getrunken, alcWirkt, nuechtern);
            case ELSE-> new Else(name, alc, ml, getrunken, alcWirkt, nuechtern);
        };
    }
}
