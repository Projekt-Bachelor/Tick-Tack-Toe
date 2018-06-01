package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

import static de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.Spielbrett.*;

public class Spiel {

    boolean Anfaenger;
    Stein[][] aktuellesFeld = Spielbrett.feld;
    //int size = Spielbrett.check();
    int size = 3;

    boolean anfaenger() {
        Random rand = new Random();
        Anfaenger = rand.nextBoolean();
        ;
        return Anfaenger;

    }

    boolean setzen() {
        if (Spielbrett.leeresFeld == true) {
            neuesFeld();
            return false;
        } else return true;

    }

    /* Alle Felder gefüllt */
    boolean feldVoll() {
        return false;
    }


    /* falls ein neues Spiel erstellt werden soll oder der Reset Botton verwendet wird */
    void neuesFeld() {
        Arrays.fill(Spielbrett.feld, null);

    }

    /* Prüft, ob es eine 3er Reihe gibt, und jemand gewonnen hat */
    public boolean feld_pruefen() {
        int XCounter = 0;
        int OCounter = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (aktuellesFeld[i][j] == Stein.Kreis) OCounter++;
                else if (aktuellesFeld[i][j] == Stein.Kreuz) {
                    XCounter++;
                }
            }
            if (XCounter == size || OCounter == size) return true;
            break;
        }
        OCounter = 0;
        XCounter = 0;

        /*Diagonal links prüfen*/
        for (int i = size - 1, j = size - 1; i >= 0; i--, j--) {
            if (aktuellesFeld[i][j] == Stein.Kreis) OCounter++;
            else if (aktuellesFeld[i][j] == Stein.Kreuz) XCounter++;
        }
        if (OCounter == size || XCounter == size) return true;

        /*Diagonal rechts prüfen */

        for (int i = 0, j = 0; i < size; i++, j++) {
            if (aktuellesFeld[i][j] == Stein.Kreis) OCounter++;
            else if (aktuellesFeld[i][j] == Stein.Kreuz) XCounter++;
        }
        if (OCounter == size || XCounter == size) return true;


        return false;


    }
}

