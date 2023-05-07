package de.htwberlin.WebTech.WebTechSoSe2023;

import de.htwberlin.WebTech.WebTechSoSe2023.domain.Drink;
import de.htwberlin.WebTech.WebTechSoSe2023.domain.DrinkService;
import de.htwberlin.WebTech.WebTechSoSe2023.domain.IDrink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WebTechSoSe2023ApplicationTests {

	@Test
	@DisplayName("db-Test sollte ein IDrink Objekt speichern und laden")
	void dbTest() {
		Drink zuSpeichern = new Drink();
		zuSpeichern.setName("Wahrsteiner");
		zuSpeichern.setAlc(BigDecimal.valueOf(25.0));
		zuSpeichern.setMl(BigDecimal.valueOf(500.0));
		zuSpeichern.setGetrunken();
		zuSpeichern.setAlcWirkt(zuSpeichern.getGetrunken());
		zuSpeichern.setNuechtern(zuSpeichern.getAlcWirkt());
		zuSpeichern.build();

		Long id = zuSpeichern.getId();

		DrinkService ds = new DrinkService();
		ds.save(zuSpeichern);
		IDrink geladen = ds.get(id);

		assertEquals(zuSpeichern.getNuechtern(), geladen.getNuechtern());
	}

}
