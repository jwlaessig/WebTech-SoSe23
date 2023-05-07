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

Ich weiß nicht, wie weit der HelloController richtig ist.
Also wir müssen noch eine Rest Schnittstelle mit PostMapping zum speichern 
eines Objektes anlegenn und eine GetMapping zum laden für Meilenstein 2.
Das gehört ja zum HelloController, weil wir Daten über den LocalPort:8080 
eingeben müssen und diese in der Datenbank speichern.

Für die build() Methode um ein normales Objekt zu speichern
braucht man: name, alc, ml, getrunken, alcWirkt, nuechtern.
LocalDateTime getrunken wird erzeugt mit der setGetrunken Methode.
Um alc zu berechnen braucht man die Mehthode setAlc(BigDecimal ml, BigDecimal alcGehalt).
Alc ist der Alkohol, der Abgebaut wird, alcGehalt sind die Prozente des Alkohol.
die LocalDateTime alcWirkt und LocalDateTime nuechtern werden wieder mit den set Mehtoden erzeugt.

Um ein Objekt über build() anzulegen braucht man also setName, setMl, set alcGehalt, setGetrunken 
und mit deren Hilfe werden setAlc, setAlcWirkt und setNuechtern berechnet.
In dem ApplicationTest kannst du dir angucken, wie die Methoden aufgerufen werden müssen, damit
alles funktioniert.

Also nun die html Oberfläche für die Eingabe der drei Werte name, alkGehalt und ml
diese für einem build() fertig machen mit den anderen Methoden und speichern (mit .save(Objekt)).
Dann eine weitere html Oberfläche um ein Objekt aus der Datenbank zu laden.