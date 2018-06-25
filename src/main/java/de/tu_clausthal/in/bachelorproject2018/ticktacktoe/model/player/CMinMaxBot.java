package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.player;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.ISpieleBrett;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.EItem;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.IItem;


/**
 * Klasse des Min-Max-Bot
 */
public final class CMinMaxBot extends IBasePlayer
{
    /**
     * Konstruktor
     *
     * @param p_value Item (Kreuz / Kreis), das durch den Spieler verwendet wird
     */
    public CMinMaxBot()
    {
        super( "Min-Max-Bot", EItem.KREIS );
    }

    @Override
    public void accept( final ISpieleBrett p_brett )
    {
        // @todo hier muss die Berechnung des Min-Max-Algorithmus hin
    }

}
