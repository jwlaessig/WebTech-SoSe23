package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;

public class Likoer extends Drink{
    public Likoer(String name, BigDecimal alc, Integer ml) {
        super(name, alc, ml);
    }

    @Override
    public String toString() {
        return this.getName() + " - Likoer";
    }

    @Override
    public IDrink.Category getCategory() {
        return IDrink.Category.LIKOER;
    }
}
