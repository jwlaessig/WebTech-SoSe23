package de.htwberlin.WebTech.WebTechSoSe2023.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDrinkRepo extends JpaRepository<Drink, Long> {
}
