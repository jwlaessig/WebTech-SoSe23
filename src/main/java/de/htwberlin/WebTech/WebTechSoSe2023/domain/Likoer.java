package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Likoer extends Drink{
    public Likoer(String name, BigDecimal alc, Integer ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern) {
        super(name, alc, ml, getrunken, alcWirkt, nuechtern);}

    public Likoer() {

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
