package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.IItem;

import java.util.concurrent.atomic.AtomicReference;


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
     * liefert zurück welcher Bot gestartet wird
     *
     * @return welcher Bot
     */
    String type();


    /**
     * prüft ob ein Feld gesetzt ist
     *
     * @param p_x X Position
     * @param p_y Y Position
     * @return frei oder nicht
     */
    boolean isempty( final int p_x, final int p_y );

    /**
     * prüft ob das Feld in das gesetzt werden soll leer ist und setzt wenn dies der Fall ist
     * prüft nach dem Setzen, ob das Spiel durch ein Unentschieden oder ein Sieg beendet wird
     *
     * @param p_item
     * @return [konnte gesetzt werden, gewonnen?, unentschieden?]
     */
    boolean set(final IItem p_item );

    boolean checkWin();

    boolean checkDraw();

    AtomicReference<IItem>[][] getM_elements();
}
