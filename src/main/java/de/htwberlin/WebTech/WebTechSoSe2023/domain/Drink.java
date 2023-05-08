package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Entity
public class Drink implements IDrink{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal alc;
    private BigDecimal alcGehalt;
    private BigDecimal ml;
    private LocalDateTime getrunken;
    private LocalDateTime alcWirkt;
    private LocalDateTime nuechtern;


    public Drink() {
    }

    public Drink(String name, BigDecimal alc, BigDecimal ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern) {
        this.name = name;
        this.alc = alc;
        this.ml = ml;
        this.getrunken = getrunken;
        this.alcWirkt = alcWirkt;
        this.nuechtern = nuechtern;
    }

    //build für die Getränke die geladen wurden
    @Override
    public Drink build(String name, BigDecimal alc, BigDecimal ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern){
        return new Drink(name, alc, ml, getrunken, alcWirkt, nuechtern);
    }

    //build für die Getränke die getrunken wurden; Attribute werden durch die set Methoden gesetzt
    @Override
    public Drink build(){
        return new Drink(name, alc, ml, getrunken, alcWirkt, nuechtern);
    }

    public Drink(String name, BigDecimal alc){
        this.name = name;
        this.alc = alc;
    }

    public Drink(String name, BigDecimal alc, BigDecimal ml) {
        this.name = name;
        this.alc = alc;
        this.ml = ml;
    }

    public Drink build(String name, BigDecimal alc, BigDecimal ml){
        return new Drink(name, alc, ml);
    }

    //build für die Map von gespeicherten Getränken (Drinks)
    @Override
    public Drink build(String name, BigDecimal alc){
        return new Drink(name, alc);
    }

    @Override
    public Long getId() { return id; }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getAlc() {
        return alc;
    }

    @Override
    public BigDecimal getAlcGehalt() { return alcGehalt; }

    @Override
    public BigDecimal getMl() {return ml;}

    @Override
    public LocalDateTime getGetrunken() { return getrunken; }

    @Override
    public LocalDateTime getAlcWirkt() { return alcWirkt; }

    @Override
    public LocalDateTime getNuechtern() { return nuechtern; }

    @Override
    public String toString() { return this.getName(); }

    private final BigDecimal alcAbbauRateProStunde = BigDecimal.valueOf(15.0);
    private final BigDecimal alcAbbauRateProMinute = alcAbbauRateProStunde.divide(BigDecimal.valueOf(60.0));

    @Override
    public void setId(Long id) { this.id = id; }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAlc(BigDecimal alcGehalt, BigDecimal ml) { this.alc = ml.divide(BigDecimal.valueOf(100)).multiply(alcGehalt); }

    @Override
    public void setAlcGehalt(BigDecimal aG) { this.alcGehalt = aG; }

    @Override
    public void setMl(BigDecimal ml) { this.ml = ml; }

    @Override
    public void setGetrunken() {
        this.getrunken = LocalDateTime.now();
    }

    @Override
    public void setAlcWirkt(LocalDateTime sAW) {
        this.alcWirkt = sAW.plusHours(1);
    }

    // hier muss noch eingebaut werden, dass der wert von nuechtern der
    // vorherigen trinkeinlage eingearbeitet werden muss.
    // dazu ist aber die Datenbank nötig.
    @Override
    public void setNuechtern(LocalDateTime sN) {
        int hours = 0;
        int minutes = 0;

        BigDecimal ausnuechtern = alc;

        while (ausnuechtern.compareTo(alcAbbauRateProStunde) >= 0 || ausnuechtern.compareTo(alcAbbauRateProMinute) >= 0) {
            if (ausnuechtern.compareTo(alcAbbauRateProStunde) >= 0) {
                ausnuechtern = ausnuechtern.subtract(alcAbbauRateProStunde);
                hours = hours + 1;
            } else {
                ausnuechtern = ausnuechtern.subtract(alcAbbauRateProMinute);
                minutes = minutes + 1;
            }
            if (ausnuechtern.compareTo(BigDecimal.ZERO) < 0) {
                break;
            }
        }

        this.nuechtern = sN.plusHours(hours).plusMinutes(minutes);
    }



}
