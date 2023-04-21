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

