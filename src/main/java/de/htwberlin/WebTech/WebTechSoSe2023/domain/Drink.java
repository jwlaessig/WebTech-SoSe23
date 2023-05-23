package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Component
public class Drink implements IDrink{
    @Id      //Primärschlüssel der Entität
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // um die Generierungsstrategie für das annotierte Feld oder die annotierte Eigenschaft festzulegen.
    private Long id;
    private String name;
    private BigDecimal alc;
    private BigDecimal alcGehalt;
    private BigDecimal ml;
    private LocalDateTime getrunken;
    private LocalDateTime alcWirkt;
    private LocalDateTime nuechtern;

    @Transient     //wird nicht in der Datenbank gespeichert oder abgerufen
    @Autowired
    DrinkService drinkService;

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
    public Drink(String name, BigDecimal alc, BigDecimal ml, LocalDateTime getrunken, LocalDateTime alcWirkt) {
        this.name = name;
        this.alc = alc;
        this.ml = ml;
        this.getrunken = getrunken;
        this.alcWirkt = alcWirkt;
    }

    //build für die Getränke die geladen wurden
    @Override
    public Drink build(String name, BigDecimal alc, BigDecimal ml, LocalDateTime getrunken, LocalDateTime alcWirkt, LocalDateTime nuechtern){
        return new Drink(name, alc, ml, getrunken, alcWirkt, nuechtern);
    }
    @Override
    public Drink build(String name, BigDecimal alc, BigDecimal ml, LocalDateTime getrunken, LocalDateTime alcWirkt){
        return new Drink(name, alc, ml, getrunken, alcWirkt);
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

    //build für die Map von gespeicherten Getränken (Drinks)
    @Override
    public Drink build(String name, BigDecimal alc){
        return new Drink(name, alc);
    }

    //Konstruktor für das Einlesen der Daten für die PostMapping Methode saveDrink
    public Drink(String name, BigDecimal alcGehalt, BigDecimal ml) {
        this.name = name;
        this.alcGehalt = alcGehalt;
        this.ml = ml;
    }

    @Override
    public Drink build(String name, BigDecimal alcGehalt, BigDecimal ml){
		return new Drink(name, alcGehalt, ml);
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
        final BigDecimal alcAbbauRateProStunde = BigDecimal.valueOf(15.0);
        final BigDecimal alcAbbauRateProMinute = alcAbbauRateProStunde.divide(BigDecimal.valueOf(60.0));

        int hours = 0;
        int minutes = 0;
        Drink aktuell = new Drink();
        aktuell.setAlc(getAlcGehalt(), getMl());
        aktuell.build(aktuell.getName(), aktuell.getAlc(), aktuell.getMl(), aktuell.getGetrunken(), aktuell.getAlcWirkt());

        List<Drink> datenbank = drinkService.getAll();
        Comparator<Drink> drinkComparator = Comparator.comparing(Drink::getNuechtern);
        Drink newestDrink = Collections.max(datenbank, drinkComparator);
        LocalDateTime zeit = newestDrink.getNuechtern();

        BigDecimal ausnuechtern = getAlc();

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

            if (zeit.compareTo(aktuell.getGetrunken()) < 0) {
            this.nuechtern = sN.plusHours(hours).plusMinutes(minutes);
            }
            else{
                Duration duration=Duration.between(aktuell.getGetrunken(), zeit);
                this.nuechtern = sN.plusHours(hours).plusMinutes(minutes).plus(duration);
            }


    }
}

//setNuechtern Methode in die Drink Service Klasse und set Nuechtern manuell Wert eingeben
