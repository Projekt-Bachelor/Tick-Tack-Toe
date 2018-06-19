# Bachelorproject 2018 - Tic-Tack-Toe
### Wichtige Links
- Dokumentation: https://projekt-bachelor.github.io/Tick-Tack-Toe/sources/
- Github Page: https://projekt-bachelor.github.io/Tick-Tack-Toe/
- Heroku-App:https://thawing-journey-93339.herokuapp.com/
### Grobe Idee 

Webaplikation, die ein Tic Tac Toe Spiel anbietet. Hierbei soll im weiteren Verlauf das Spielen gegen einen Bot der lernt umgesetzt werden.


### Spielregeln

* Spielfeld ist 3 x 3 
* Abwechseld werden Kreuze und Kreise in die freien Felder gesetzt 
* Falls ein Spieler 3 Zeichen in entweder einer Zeile, Spalte oder Diagonalen setzt, gewinnt dieser das Spiel
* Falls  eine 3 Reihe nicht mehr erreichbar ist beziehungsweise alle neun Felder ohne 3er Serie belegt sind &rarr; Unentschieden

### Strategie 

Es gibt 255.168 Möglichkeiten wie das Spiel ausgehen kann. Dabei ist die Wahrscheinlichkeit, dass der erste Spieler gewinnt um ca 60% höher, als der zweite. Daher soll der Algorithmus minimax verwendet werden.

### Verwendete Tools

* Framework: Spring-Boot 
* Sprache: Java 8, Java Skript
* Graphische Oberfläche:HTML, CSS, Java Template Engine
* Fonst für X & O: selbst erstellt
* Sweet-Alert eingebunden: https://sweetalert.js.org/guides/


### Meilstones

- Model: Daten des Spiels
	- Spielbrett
	- Regeln der Spielelogik
	- Spieler mit Symbol
	- Minimax Algorithmus für den Bot in eingener Botklasse
- View: Visualisierung

- Controller: Verknüpfung View und Spielelogik



## Aufgaben zum nächsten Termin

1. Github und Git (add/commit/push/pull)
2. Maven, Eclipse, Intellij
3. App clone von Github
**Pro Team**  
1. eine Person ist für Heroku Deployment zuständig
&rarr; Marie

## Notizen
### Projektaufbau  

&rarr; REST Test (Abfrage der Daten des Controllers)  
&rarr; View (HTML, Game-Engine)  
&rarr; Logik


