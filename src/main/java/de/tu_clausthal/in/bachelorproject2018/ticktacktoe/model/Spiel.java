package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model;

import java.util.Random;
import java.util.Arrays;

import static de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.Spiel.neuesSpiel;
import static de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.Stein.Kreis;

public class Spiel {
    public static boolean Runde = false;
    public static boolean Anfaenger;
    static Stein[][] aktuellesFeld = Spielbrett.feld;
    //int size = Spielbrett.check();
    int size = 3;


    boolean anfaenger() {
        //0 ist der Kreis und 1 ist X
        Random rand = new Random();
        Anfaenger = rand.nextBoolean();
        ;
        return Anfaenger;

    }

    public static void neuesSpiel(){
        Runde=true;
        neuesFeld();
    }

    public static void setzen(int x, int y) {
        if(Runde == false){
            neuesSpiel();
        }
        else {
            System.out.println("Es wurde kein Spielstein gesetzt! Setzten sie bitte zum Vortfahren einen Spielstein");
            //int x = -1;
            //int y=-1;
            Spiel.setzen(x, y);// Benutzer setzt einen Stein
        }
        aktuellesFeld[x][y]=Kreis;
    }

    /* Alle Felder gefüllt */
    boolean feldVoll() {
        return false;
    }


    /* falls ein neues Spiel erstellt werden soll oder der Reset Botton verwendet wird */
    public static void neuesFeld() {
        Arrays.fill(Spielbrett.feld, null);

    }

    /* Prüft, ob es eine 3er Reihe gibt, und jemand gewonnen hat */
    public boolean feld_pruefen() {
        int XCounter = 0;
        int OCounter = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (aktuellesFeld[i][j] == Kreis) OCounter++;
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
            if (aktuellesFeld[i][j] == Kreis) OCounter++;
            else if (aktuellesFeld[i][j] == Stein.Kreuz) XCounter++;
        }
        if (OCounter == size || XCounter == size) return true;

        /*Diagonal rechts prüfen */

        for (int i = 0, j = 0; i < size; i++, j++) {
            if (aktuellesFeld[i][j] == Kreis) OCounter++;
            else if (aktuellesFeld[i][j] == Stein.Kreuz) XCounter++;
        }
        if (OCounter == size || XCounter == size) return true;


        return false;


    }
}

