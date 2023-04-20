package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Drink implements IDrink{
    private final String name;
    private final BigDecimal alc;
    private final Integer ml;

    public Drink(String name, BigDecimal alc, Integer ml) {
        this.name = name;
        this.alc = alc;
        this.ml = ml;
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
