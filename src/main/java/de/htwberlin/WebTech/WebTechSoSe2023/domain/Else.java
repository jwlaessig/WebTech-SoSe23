package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;

public class Else extends Drink{
    public Else(String name, BigDecimal alc, Integer ml) {
        super(name, alc, ml);
    }

    @Override
    public String toString() {
        return this.getName() + " - Else";
    }

    @Override
    public IDrink.Category getCategory() {
        return Category.ELSE;
    }
}
