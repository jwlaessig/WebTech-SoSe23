package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Schnaps extends Drink{
    public Schnaps(String name, BigDecimal alc, Integer ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern) {
        super(name, alc, ml, getrunken, alcWirkt, nuechtern);}

    public Schnaps() {

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
