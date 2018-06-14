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
    public CMinMaxBot( final EItem p_value )
    {
        super( "Min-Max-Bot", p_value );
    }

    @Override
    public void accept( final ISpieleBrett p_iSpieleBrett )
    {
        // @todo hier muss die Berechnung des Min-Max-Algorithmus hin
    }

}
