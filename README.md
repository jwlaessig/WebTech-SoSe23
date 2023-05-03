# WebTech-SoSe23
Berkem Kalkan - Matrikelnummer: 585169 E-Mail: berkemkalkan@gmail.com

und 

Jürgen Willy Lässig - Matrikelnummer: 573711 E-Mail: s0573711@htw-berlin.de


Wir bauen eine App, die den Alkoholkonsum erfasst und berechnet, 
wann man wieder Auto fahren kann.
Dabei sind mögliche Ausgaben, was man alles getrunken hat, 
wie viel Promille derzeit im Blut sind und wann man wieder Auto fahren darf,
weil Null Promille erreicht sind.
Eingabemöglichkeiten sind der Name des Getränks, 
wodurch der Alkoholgehalt aus einer Map gesucht wird und die Menge in Milliliter.
Oder Alkoholgehalt und Menge in Milliliter und der Name werden eingegeben, 
wenn der Name nicht in der Map steht.

----------------------------------------------------------------------

Die DrinkBuilder Klasse ist zum speichern der Werte, die folgend an den Konstruktor 
der jeweiligen Category des Getränks übergeben werden sollen.
IDrinks ist das Interface um auf alle Categories zugreifen zu können mittels einer Liste.
Categories und KOnstruktoren der Unterklassen sind Bier, Likoer, Schnaps und Else für Wein 
oder andere Getränke, die unterschiedlich hohen Alkoholgehalt haben.
Drinks ist die Übersicht in Form einer Map über die gespeicherten Getränke.
Drinks ist der Konstruktor der Oberklasse der Getränke. 

---------------------------------------------------------------------

für Berkem:

Du kannst die zweite Abgabe programmieren. Und zwar möchte ich erstmal das speichern und laden
in der Datenbank H2 implementiert haben, das ist auch Thema der 2ten Abgabe.
Dazu zwei Listen erstellen des Datentyp IDrink (Interface), damit alle Getränke gespeichert werden
können unabhängig von dem Konstruktor. Diese kannst du gespeichert und geladen nennen.
Dann die build Methode in der Klasse DrinkBuilder anpassen, dass das erstellte Objekt in der 
Liste gespeichert geaddet wird.
Drittens; das neue Objekt der Liste gespeichert in die Datenbank einfügen.
Viertens; das letzte Objekt aus der Datenbank laden (SQL sortiert nach LocalDateTime nuechtern DESC)
und in die Liste geladen hinzufügen. Dazu solltest du wieder die build Methode der Klasse
DrinkBuilder nutzen.
Das war es. Wenn du neue Methoden hinzufügst, dann gucke bitte, ob es sinnvoll ist eine neue Klasse
dafür zu erstellen. Vielleicht ist auch eine neue Klasse für die beiden neuen Listen sinnvoll. 
Überleg es dir. Danke im Voraus.

----------------------------------------------------------------

H2 config für windows:
Systemumgebungsvariablen - Umgebungsvariablen - neu erstellen für
DATASOURCE_URL      - BSP: jdbc:h2:file:/webtech_db/db
DATASOURCE_USERNAME
DATASOURCE_PASSWORD

oder in intellij
WebtechSoSe2023Application - Edit Configuration - Modify Option - Environment Variables - edit Environment Variables (add)
DATASOURCE_URL      - BSP: jdbc:h2:file:/webtech_db/db
DATASOURCE_USERNAME
DATASOURCE_PASSWORD

----------------------------------------------------------------