package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Drink implements IDrink{
    private final String name;
    private final BigDecimal alc;
    private final Integer ml;
    private final LocalDateTime getrunken;
    private final LocalDateTime alcWirkt;
    private final LocalDateTime nuechtern;

    public Drink(String name, BigDecimal alc, Integer ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern) {
        this.name = name;
        this.alc = alc;
        this.ml = ml;
        this.getrunken = getrunken;
        this.alcWirkt = alcWirkt;
        this.nuechtern = nuechtern;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getAlc() {
        return alc;
    }

    @Override
    public Integer getMl() {return ml;}

    @Override
    public LocalDateTime getGetrunken() { return getrunken; }

    @Override
    public LocalDateTime getAlcWirkt() { return alcWirkt; }

    @Override
    public LocalDateTime getNuechtern() { return nuechtern; }

    @Override
    public Category getCategory() {
        return null;
    }

    @Override
    public String toString() { return this.getName(); }

    @Override
    public int hashCode() {
        return Objects.hash(getName().trim(), alc.doubleValue());
    }
}
