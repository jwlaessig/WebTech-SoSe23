package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IDrink {

    Long getId();

    String getName();

    BigDecimal getAlc();

    BigDecimal getMl();

    LocalDateTime getGetrunken();

    LocalDateTime getAlcWirkt();

    LocalDateTime getNuechtern();

    void setId(Long id);

    void setName(String name);

    void setAlc(String alc);

    void setMl(String ml);

    void setGetrunken();

    void setAlcWirkt();

    void setNuechtern();


    Drink build(String name, BigDecimal alc);

    Drink build(String name, BigDecimal alc, BigDecimal ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern);

    Drink build();


    String toString();
}