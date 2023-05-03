package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;
import java.util.Map;

public class Drinks {
    private Drinks() {}
    private static Drinks theInstance = null;

    public static Drinks getInstance() {
        if(theInstance == null) theInstance = new Drinks();
        return theInstance;
    }
    private IDrink builder = new Drink();

    private Map<Long, IDrink> articles = Map.of(
            1L, builder.build("Kindl", BigDecimal.valueOf(4.9)),
            2L, builder.build("Wahrsteiner", BigDecimal.valueOf(4.8)),
            3L, builder.build("Vodka", BigDecimal.valueOf(40.0)),
            4L, builder.build("Whiskey", BigDecimal.valueOf(40.0)),
            5L, builder.build("Feigling", BigDecimal.valueOf(20.0)));

    public Map<Long, IDrink> getAllArticles() { return articles; }
}
