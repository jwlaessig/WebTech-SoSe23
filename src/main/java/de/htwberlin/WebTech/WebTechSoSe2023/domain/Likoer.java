package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Likoer extends Drink{
    public Likoer(String name, BigDecimal alc, Integer ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern) {
        super(name, alc, ml, getrunken, alcWirkt, nuechtern);}

    @Override
    public String toString() {
        return this.getName() + " - Likoer";
    }

    @Override
    public IDrink.Category getCategory() {
        return IDrink.Category.LIKOER;
    }
}
