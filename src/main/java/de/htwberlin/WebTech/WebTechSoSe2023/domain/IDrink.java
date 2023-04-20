package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;

public interface IDrink {

    String getName();

    BigDecimal getAlc();

    Category getCategory();

    enum Category{
        BIER,
        SCHNAPS,
        LIKOER
    }
}
