package de.htwberlin.WebTech.WebTechSoSe2023;

import de.htwberlin.WebTech.WebTechSoSe2023.domain.Drink;
import de.htwberlin.WebTech.WebTechSoSe2023.domain.DrinkService;
import de.htwberlin.WebTech.WebTechSoSe2023.domain.IDrinkRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class WebTechSoSe2023ApplicationTests {

	@Autowired
	private DrinkService drinkService;

	@MockBean
	private IDrinkRepo drinkRepo;

	@Test
	@DisplayName("db-Test sollte ein Drink Objekt speichern und laden")
	void dbTest() {
		Drink zuSpeichern = new Drink();
		zuSpeichern.setName("Wahrsteiner");
		zuSpeichern.setMl(BigDecimal.valueOf(500.0));
		zuSpeichern.setAlcGehalt(BigDecimal.valueOf(4.8));
		zuSpeichern.build(zuSpeichern.getName(), zuSpeichern.getAlcGehalt(), zuSpeichern.getMl());

		Long id = zuSpeichern.getId();

		// chatGPT hat die folgenden zwei Zeilen vorgeschlagen, um die Datenbank zu simulieren (Mocking)
		when(drinkRepo.findById(id)).thenReturn(Optional.of(zuSpeichern));
		when(drinkRepo.save(zuSpeichern)).thenReturn(zuSpeichern);

		drinkService.speichern(zuSpeichern);
		Drink geladen = drinkService.get(id);

		assertEquals(zuSpeichern.getNuechtern(), geladen.getNuechtern());
	}

	@Test
	@DisplayName("Alkohol wirkt Berechnung wird getestet")
	void getrunkenTest() {
		Drink zuSpeichern = new Drink();
		zuSpeichern.setName("Kindl");
		zuSpeichern.setMl(BigDecimal.valueOf(500.0));
		zuSpeichern.setAlcGehalt(BigDecimal.valueOf(4.8));
		zuSpeichern.setGetrunken();
		zuSpeichern.setAlcWirkt(zuSpeichern.getGetrunken());
		zuSpeichern.setNuechtern(zuSpeichern.getAlcWirkt());
		zuSpeichern.build();

		assertEquals(zuSpeichern.getAlcWirkt(), zuSpeichern.getGetrunken().plusHours(1));
	}

	@Test
	@DisplayName("Ausnüchtern wird berechnet wird getestet")
	void nuechternTest() {
		Drink zuSpeichern = new Drink();
		zuSpeichern.setName("Limonchello");
		zuSpeichern.setMl(BigDecimal.valueOf(100.0));
		zuSpeichern.setAlcGehalt(BigDecimal.valueOf(30.0));
		zuSpeichern.setGetrunken();
		zuSpeichern.setAlcWirkt(zuSpeichern.getGetrunken());
		zuSpeichern.setNuechtern(zuSpeichern.getAlcWirkt());
		zuSpeichern.build();

		assertEquals(drinkService.berechneNuechtern(drinkService.berechneAlcWirkt(zuSpeichern.getGetrunken())), zuSpeichern.getAlcWirkt().plusHours(2));
	}

}