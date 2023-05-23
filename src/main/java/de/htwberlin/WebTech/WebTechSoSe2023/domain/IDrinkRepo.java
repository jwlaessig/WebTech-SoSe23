package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDrinkRepo extends CrudRepository<Drink, Long> {
}
