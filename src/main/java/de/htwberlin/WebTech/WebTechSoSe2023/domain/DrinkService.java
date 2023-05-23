package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class DrinkService {

    @Autowired
    private IDrinkRepo repo;

    public Drink save(Drink zuSpeichern){
        Drink drink = new Drink();
        drink.setName(zuSpeichern.getName());
        drink.setAlcGehalt(zuSpeichern.getAlcGehalt());
        drink.setMl(zuSpeichern.getMl());
        drink.setAlc(drink.getAlcGehalt(), drink.getMl());
        drink.setGetrunken();
        drink.setAlcWirkt(drink.getGetrunken());
        drink.setNuechtern(drink.getAlcWirkt());
        drink.build();
        return repo.save(drink);
    }

    public Drink get(Long id){
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Drink> getAll() {
        return repo.findAll();
    }
}