package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DrinkBuilder {

    private String name;
    private BigDecimal alc;
    private Integer ml;

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

    public Drink build(Drink.Category cat) {
        return switch (cat) {
            case BIER -> new Bier(name, alc, ml);
            case SCHNAPS -> new Schnaps(name, alc, ml);
            case LIKOER -> new Likoer(name, alc, ml);
            case ELSE-> new Else(name, alc, ml);
        };
    }
}
