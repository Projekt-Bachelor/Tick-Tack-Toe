package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.IItem;


/**
 * Interface des Spielebretts
 */
public interface ISpieleBrett
{
    /**
     * Name des Spielebretts
     */
    String name();

    /**
     * liefert die Breite des Spielefelds
     *
     * @return Breite
     */
    int width();

    /**
     * liefert die Höhe des Spielefelds
     *
     * @return Höhe
     */
    int height();

    /**
     * prüft ob ein Feld gesetzt ist
     *
     * @param p_x X Position
     * @param p_y Y Position
     * @return frei oder nicht
     */
    boolean isempty( final int p_x, final int p_y );

    /**
     * setzt ein Feld un liefert zurück, ob gesetzt werden konte
     *
     * @param p_item Item (Kreuz / Kreis)
     * @return konnte gesetzt werden oder nicht
     */
    boolean set( final IItem p_item );

}
