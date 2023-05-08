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
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Start";
    }

    @Autowired
    private DrinkService drinkService;


      //für den browser zum abfragen: http://localhost:8080/drinks?id=1001&name=Wahrsteiner&alcGehalt=4.8&ml=500.0
      //eventuell muss die tabelle drink in der datenbank erst gelöscht werden, weil sie mit anderen attributen
      //erstellt wurde, als jetzt eingetragen werden. SQL: drop table drink
     //        --- nur testweise ---
      @GetMapping(path="/drinks/save")
      public String saveDrink(@RequestParam("name") String name,
                              @RequestParam("alcGehalt") BigDecimal alcGehalt,
                              @RequestParam("ml") BigDecimal ml) {
          Drink drink = new Drink();
          drink.setName(name);
          drink.setAlcGehalt(alcGehalt);
          drink.setMl(ml);
          drink.build(drink.getName(), drink.getAlc(), drink.getMl());
          Drink gespeichert = drinkService.save(drink);
          return String.format("ID: %s, nüchtern: %s", gespeichert.getId(), drink.getNuechtern());
      }

      //benötigt die Werte Name, alcGehalt und ml für den (@RequestBody Drink request)
      //der Konstruktor kann mit der Methode build(name, alc, ml) aufgerufen werden.
       @PostMapping(path = "/drinks")
        public ResponseEntity<Void> saveDrink(@RequestBody Drink request) throws URISyntaxException {
            var drink = drinkService.save(request);
            URI uri = new URI( "/drinks" + drink.getId());
            return ResponseEntity.created(uri).build();
        }


     //für den browser zum abfragen: http://localhost:8080/drinks/1
     @GetMapping(path="/drinks/{id}") public String loadDrink(@PathVariable("id") Long id){
     Drink geladen = drinkService.get(id);
     return String.format("ID: %s, Name: %s, nüchtern: %s", id, geladen.getName(), geladen.getNuechtern());
     }

}