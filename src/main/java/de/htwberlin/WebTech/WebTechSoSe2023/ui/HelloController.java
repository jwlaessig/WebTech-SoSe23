package de.htwberlin.WebTech.WebTechSoSe2023.ui;

import de.htwberlin.WebTech.WebTechSoSe2023.domain.Drink;
import de.htwberlin.WebTech.WebTechSoSe2023.domain.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Start";
    }

    @Autowired
    private DrinkService drinkService;

    /**
    @PostMapping("/drinks")
    public ResponseEntity<Drink> saveDrink(@RequestParam("id") Long id,
                                           @RequestParam("name") String name,
                                           @RequestParam("alcGehalt") BigDecimal alcGehalt,
                                           @RequestParam("ml") BigDecimal ml) {
        Drink drink = new Drink();
        drink.setId(id);
        drink.setName(name);
        drink.setAlcGehalt(alcGehalt);
        drink.setMl(ml);
        drink.setAlc(drink.getAlcGehalt(), drink.getMl());
        drink.setGetrunken();
        drink.setAlcWirkt(drink.getGetrunken());
        drink.setNuechtern(drink.getAlcWirkt());
        drink.build();
        Drink saved = drinkService.save(drink);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
*/

}