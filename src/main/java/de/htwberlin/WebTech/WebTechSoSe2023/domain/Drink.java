package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Drink implements IDrink{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private BigDecimal alc;
    private Integer ml;
    private LocalDateTime getrunken;
    private LocalDateTime alcWirkt;
    private LocalDateTime nuechtern;

    public Drink() {
    }

    public Drink(String name, BigDecimal alc, Integer ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern) {
        this.name = name;
        this.alc = alc;
        this.ml = ml;
        this.getrunken = getrunken;
        this.alcWirkt = alcWirkt;
        this.nuechtern = nuechtern;
    }

    //build für die Getränke die geladen wurden
    @Override
    public Drink build(String name, BigDecimal alc, Integer ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern){
        return new Drink(name, alc, ml, getrunken, alcWirkt, nuechtern);
    }

    //build für die Getränke die getrunken wurden; Attribute werden durch die set Mehtoden gesetzt
    @Override
    public Drink build(){
        return new Drink(name, alc, ml, getrunken, alcWirkt, nuechtern);
    }

    public Drink(String name, BigDecimal alc){
        this.name = name;
        this.alc = alc;
    }

    //build für die Map von gespeicherten Getränken (Drinks)
    @Override
    public Drink build(String name, BigDecimal alc){
        return new Drink(name, alc);
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

    public String toString() { return this.getName(); }

    private BigDecimal alcAbbauRateProStunde = BigDecimal.valueOf(15);
    private BigDecimal alcAbbauRateProMinute = BigDecimal.valueOf(15/60);

    public void setName(String name) {
        this.name = name;
    }
    public void setAlc(String alc) {
        this.alc = new BigDecimal(alc);
    }

    public void setMl(String ml) {
        this.ml = new Integer(ml);
    }

    public void setGetrunken() {
        this.getrunken = LocalDateTime.now();
    }

    public void setAlcWirkt() {
        this.alcWirkt = LocalDateTime.now().plusHours(1);
    }

    // hier muss noch eingebaut werden, dass der wert von nuechtern der
    // vorherigen trinkeinlage eingearbeitet werden muss.
    // dazu ist aber die Datenbank nötig.
    public void setNuechtern() {
        int hours = 0;
        int minutes = 0;

        BigDecimal ausnuechtern = alc;

        while (ausnuechtern.compareTo(alcAbbauRateProStunde) >= 0) {
            ausnuechtern = ausnuechtern.subtract(alcAbbauRateProStunde);
            hours = hours + 1;
        }

        while (ausnuechtern.compareTo(alcAbbauRateProMinute) >= 0) {
            ausnuechtern = ausnuechtern.subtract(alcAbbauRateProMinute);
            minutes = minutes + 1;
        }

        this.nuechtern = alcWirkt.plusHours(hours).plusMinutes(minutes);
    }



}
