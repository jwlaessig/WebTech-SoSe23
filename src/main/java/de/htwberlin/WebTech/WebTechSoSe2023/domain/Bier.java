package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bier extends Drink{
    public Bier(String name, BigDecimal alc, Integer ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern) {
        super(name, alc, ml, getrunken, alcWirkt, nuechtern);
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
