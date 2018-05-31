package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

import static de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.Spielbrett.*;

public class Spiel{

    boolean Anfaenger;
    Stein [][] aktuellesFeld =Spielbrett.feld;


    boolean anfaenger(){
        Random rand = new Random();
        Anfaenger = rand.nextBoolean();;
        return Anfaenger;

    }
    boolean setzen(){
        if (Spielbrett.leeresFeld == true){
            neuesFeld();
            return false;
        }
        else return true;

    }

    /* Alle Felder gefüllt */
    boolean feldVoll(){
        return false;
    }


    /* falls ein neues Spiel erstellt werden soll oder der Reset Botton verwendet wird */
    void neuesFeld(){
            Arrays.fill( Spielbrett.feld, null );

    }

    /* Prüft, ob es eine 3er Reihe gibt, und jemand gewonnen hat */
    boolean feld_pruefen(Stein [][] aktuellesFeld){
        /* Diagonale Spielfeld wird geprüft */
        if(Spielbrett.feld[0][0].equals(Spielbrett.feld[1][1]) && Spielbrett.feld[1][1].equals(Spielbrett.feld[2][2]))
        {
            return true;
        }
        else if(Spielbrett.feld[0][2].equals(Spielbrett.feld[1][1]) && Spielbrett.feld[1][1].equals(Spielbrett.feld[2][0]))
        {
            return true;
        }


    else return false;

    }


}
