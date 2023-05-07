package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {

    @Autowired
    @Qualifier("IDrinkRepo")
    IDrinkRepo repo;

    public Drink save(Drink zuSpeichern){
        return repo.save(zuSpeichern);
    }

    public IDrink get(Long id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException());
    }

}
