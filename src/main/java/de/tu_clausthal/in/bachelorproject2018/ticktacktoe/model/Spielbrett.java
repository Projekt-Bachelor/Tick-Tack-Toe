package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model;

public class Spielbrett {

    public static final int SPALTENZAHL = 3;
    public static final int ZEILENZAHL = 3;
    static boolean leeresFeld = true;

    /*Feld vom Typ Stein mit einer 2dim 3x3 Array*/
    static Stein[] [] feld =new Stein[SPALTENZAHL][ZEILENZAHL];


}
