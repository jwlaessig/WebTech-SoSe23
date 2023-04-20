package de.htwberlin.WebTech.WebTechSoSe2023.data;

import de.htwberlin.WebTech.WebTechSoSe2023.domain.DrinkBuilder;
import de.htwberlin.WebTech.WebTechSoSe2023.domain.IDrink;

import java.util.Map;

import static de.htwberlin.WebTech.WebTechSoSe2023.domain.IDrink.Category.*;

public class Drinks {
    private Drinks() {}
    private static Drinks theInstance = null;

    public static Drinks getInstance() {
        if(theInstance == null) theInstance = new Drinks();
        return theInstance;
    }
    private DrinkBuilder builder = new DrinkBuilder();

    private Map<Long, IDrink> articles = Map.of(
            1L, builder.setName("Kindl").setAlc("5.1").build(BIER),
            2L, builder.setName("Wahrsteiner").setAlc("4.8").build(BIER),
            3L, builder.setName("Vodka").setAlc("40.0").build(SCHNAPS),
            4L, builder.setName("Whiskey").setAlc("40.0").build(SCHNAPS),
            5L, builder.setName("Feigling").setAlc("20.0").build(LIKOER));

    public Map<Long, IDrink> getAllArticles() { return articles; }
}
