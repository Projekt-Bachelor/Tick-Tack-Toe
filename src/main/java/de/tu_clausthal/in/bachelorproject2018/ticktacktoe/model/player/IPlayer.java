package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.player;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.ISpieleBrett;

import java.util.function.Consumer;


/**
 * Interface für einen Spieler (Bot oder Mensch).
 * Das Interface erbt von Supplier, denn jeder Spieler kann einen Spielstein erzeugen
 */
public interface IPlayer extends Consumer<ISpieleBrett>
{
    /**
     * liefert den Namen des Spielers
     *
     * @return Name des Spielers
     */
    String name();


}
