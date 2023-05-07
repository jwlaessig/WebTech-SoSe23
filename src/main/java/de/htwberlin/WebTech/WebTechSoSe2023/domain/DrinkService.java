package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {

    @Autowired
    private IDrinkRepo repo;

    public Drink save(Drink zuSpeichern){
        return repo.save(zuSpeichern);
    }

    public Drink get(Long id){
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }


}
