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

    Drink build(String name, BigDecimal alc);

    Drink build(String name, BigDecimal alc, Integer ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern);

    Drink build();

}