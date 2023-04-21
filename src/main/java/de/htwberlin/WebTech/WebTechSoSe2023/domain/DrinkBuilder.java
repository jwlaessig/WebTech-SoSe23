package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class DrinkBuilder {

    private String name;
    private BigDecimal alc; // in Gramm
    // 10g entsprechen 0.5l Bier mit 5% Alkohol
    // das ist auch die durchschnittliche Alkoholabbaurate des Körpers pro Stunde
    private Integer ml;
    private LocalDateTime getrunken;
    private LocalDateTime alcWirkt;
    private LocalDateTime nuechtern;

    private BigDecimal alcAbbauRate = BigDecimal.valueOf(10);

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

    public DrinkBuilder setNuechtern() {
        this.nuechtern = LocalDateTime.now().plusHours(1);
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
