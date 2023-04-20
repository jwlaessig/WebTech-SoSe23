package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;

public class Unique extends Drink{
    public Unique(String name, BigDecimal alc, Integer ml) {
        super(name, alc, ml);
    }

    @Override
    public String toString() {
        return this.getName() + " - Unique";
    }

    @Override
    public IDrink.Category getCategory() {
        return Category.UNIQUE;
    }
}
