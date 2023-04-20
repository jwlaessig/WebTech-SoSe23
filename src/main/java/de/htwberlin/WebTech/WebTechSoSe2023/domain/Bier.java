package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;

public class Bier extends Drink{
    public Bier(String name, BigDecimal alc, Integer ml) {
        super(name, alc, ml);
    }

    @Override
    public String toString() {
        return this.getName() + " - Bier";
    }

    @Override
    public Category getCategory() {
        return Category.BIER;
    }
}
