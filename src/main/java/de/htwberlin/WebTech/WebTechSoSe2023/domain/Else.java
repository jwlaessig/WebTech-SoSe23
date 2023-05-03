package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Else extends Drink{
    public Else(String name, BigDecimal alc, Integer ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern) {
        super(name, alc, ml, getrunken, alcWirkt, nuechtern);}

    public Else() {

    }

    @Override
    public String toString() {
        return this.getName() + " - Bier";
    }

    @Override
    public IDrink.Category getCategory() {
        return Category.ELSE;
    }
}
