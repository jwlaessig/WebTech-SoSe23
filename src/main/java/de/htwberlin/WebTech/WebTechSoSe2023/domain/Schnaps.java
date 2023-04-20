package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;

public class Schnaps extends Drink{
    public Schnaps(String name, BigDecimal alc, Integer ml) {
        super(name, alc, ml);
    }

    @Override
    public String toString() {
        return this.getName() + " - Schnaps";
    }

    @Override
    public Category getCategory() {
        return Category.SCHNAPS;
    }
}
