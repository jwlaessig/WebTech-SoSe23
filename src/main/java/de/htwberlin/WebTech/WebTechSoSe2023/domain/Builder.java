package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Builder {

    private List<Drink> list = new ArrayList<>();

    public enum CreationStyle {
        PRECOMPUTED,
        DYNAMICALLY_COMPUTED
    }

    private CreationStyle creationStyle;

    public void setCreationStyle(CreationStyle creationStyle) {
        this.creationStyle = creationStyle;
    }

    public Builder add(Drink drink) {
        this.list.add(drink);
        return this;
    }

    public IDrink build() {
        checkRules();
        return creationStyle == CreationStyle.PRECOMPUTED ? buildPrecomputed() : buildDynamicallyComputed();
    }

   /** private void checkRules() {
        if(ingredients.size() < 2) throw new IllegalBurgerException("Nicht genügend Zutaten");

        // TODO hier mit ingredient.getCategory() statt mit Menu.bases und Menu.sauces arbeiten
        if(ingredients.stream().filter((ing) -> ing.getCategory().equals(Ingredient.Category.BASE)).collect(Collectors.toList()).size() > 1)
            throw new IllegalBurgerException("Zwei mal Brot-Basis in einer Burger geht nicht");

        if(ingredients.stream().filter((ing) -> ing.getCategory().equals(Ingredient.Category.SAUCE)).collect(Collectors.toList()).size() < 1)
            throw new IllegalBurgerException("Eine Burger braucht mindestens eine Sauce");
    }

    private Burger buildPrecomputed() {
        BigDecimal price = BigDecimal.ZERO;
        int calories = 0;
        List<String> ingredientNames = new ArrayList<>();

        for (Ingredient ing : ingredients) {
            price = price.add(ing.getPrice());
            calories += ing.getCalories();
            ingredientNames.add(ing.getName());
        }
        ingredients.clear();

        return new PrecomputedBurger(price, calories, ingredientNames);
    }

    private Drink buildDynamicallyComputed() {
        List<Ingredient> ingsToPass = List.copyOf(ingredients);
        ingredients.clear();
        return new DynamicallyComputedBurger(ingsToPass);
    }*/

    public Builder(CreationStyle creationStyle) {
        this.creationStyle = creationStyle;
    }

    public Builder() {
        this(CreationStyle.PRECOMPUTED);
    }
}
