package de.htwberlin.WebTech.WebTechSoSe2023;

import de.htwberlin.WebTech.WebTechSoSe2023.domain.Drink;
import de.htwberlin.WebTech.WebTechSoSe2023.domain.DrinkService;
import de.htwberlin.WebTech.WebTechSoSe2023.domain.IDrink;
import de.htwberlin.WebTech.WebTechSoSe2023.domain.IDrinkRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WebTechSoSe2023ApplicationTests {

	@Test
	@DisplayName("db-Test sollte ein IDrink Objekt speichern und laden")
	void dbTest() {
		Drink zuSpeichern = new Drink();
		zuSpeichern.setId(1001L);
		zuSpeichern.setName("Wahrsteiner");
		zuSpeichern.setAlc(String.valueOf(25));
		zuSpeichern.setMl(String.valueOf(500));
		zuSpeichern.setGetrunken();
		zuSpeichern.setAlcWirkt();
		zuSpeichern.setNuechtern();
		zuSpeichern.build();

		DrinkService ds = new DrinkService();
		ds.save(zuSpeichern);
		IDrink geladen = ds.get(1001L);

		assertEquals(zuSpeichern.getNuechtern(), geladen.getNuechtern());
	}

}
