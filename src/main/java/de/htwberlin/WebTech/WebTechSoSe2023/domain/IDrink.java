package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IDrink {

    String getName();

    BigDecimal getAlc();

    Integer getMl();

    LocalDateTime getGetrunken();

    LocalDateTime getAlcWirkt();

    LocalDateTime getNuechtern();

    Category getCategory();

    enum Category{
        BIER,
        SCHNAPS,
        LIKOER,
        ELSE
    }
}