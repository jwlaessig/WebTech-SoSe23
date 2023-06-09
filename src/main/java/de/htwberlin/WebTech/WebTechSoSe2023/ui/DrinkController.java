package de.htwberlin.WebTech.WebTechSoSe2023.ui;

import de.htwberlin.WebTech.WebTechSoSe2023.domain.Drink;
import de.htwberlin.WebTech.WebTechSoSe2023.domain.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class DrinkController {

    @GetMapping("/")
    public String index() {
        return "Start";
    }

    @Autowired
    private DrinkService drinkService;

    //benötigt die Werte Name, alcGehalt und ml für den (@RequestBody Drink request)
    @PostMapping(path = "/drinks")
    public ResponseEntity<Void> saveDrink(@RequestBody Drink request) throws URISyntaxException {
        var drink = drinkService.speichern(request);
        URI uri = new URI( "/drinks" + drink.getId());
        return ResponseEntity.created(uri).build();
    }

    /** JSON Body:

     {
     "name": "Mojito",
     "ml": 200,
     "alcGehalt": 7.5
     }

     */

    @GetMapping(path="/drinks/{id}")
    public ResponseEntity<Drink> loadDrink(@PathVariable Long id){
        ResponseEntity<Drink> response;
        Drink drink = drinkService.get(id);
        if (drink != null) {
            response = new ResponseEntity<>(drink, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping(path="/drinks")
    public ResponseEntity<List<Drink>> loadAllDrinks(){
        ResponseEntity<List<Drink>> response;
        List<Drink> drinks = drinkService.getAll();
        if (!drinks.isEmpty()) {
            response = new ResponseEntity<>(drinks, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping(path = "/drinks")
    public void deleteAll() {
        drinkService.deleteAll();
    }

    @DeleteMapping(path="/drinks/{id}")
    public void deleteDrink(@PathVariable Long id){
        drinkService.deleteById(id);
    }


    @GetMapping(path="/canDrive")
    public ResponseEntity<String> loadCanDrive(){
        ResponseEntity<String> response;
        String canDrive = drinkService.canDrive();
        if (canDrive != null) {
            response = new ResponseEntity<>(canDrive, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping(path="/countdown")
    public ResponseEntity<String> loadCountdown(){
        ResponseEntity<String> response;
        String countdown = drinkService.countdown();
        if (countdown != null) {
            response = new ResponseEntity<>(countdown, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
