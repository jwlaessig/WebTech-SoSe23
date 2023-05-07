package de.htwberlin.WebTech.WebTechSoSe2023.ui;

import de.htwberlin.WebTech.WebTechSoSe2023.domain.Drink;
import de.htwberlin.WebTech.WebTechSoSe2023.domain.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @Autowired
    private DrinkService drinkService;

    @PostMapping("/post")
    public Drink saveDrink(Drink drink) {
        return drinkService.save(drink);
    }

    @GetMapping("/get")
    public Optional<Drink> getDrink(Long id) {
        return Optional.ofNullable(drinkService.get(id));
    }

}