package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.player;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.ISpieleBrett;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.EItem;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.IItem;


/**
 * Klasse, um einen menschlichen Spieler darzustellen
 */
public final class CHuman extends IBasePlayer
{
    /**
     * Konstruktor, der nur in abgeleiteten Klassen verwendet werden kann
     *
     * @param p_name Name des Spielers
     * @param p_value Item (Kreuz / Kreis), das durch den Spieler verwendet wird
     */
    public CHuman( final String p_name, final EItem p_value )
    {
        super( p_name, p_value );
    }

    @Override
    public void accept( final ISpieleBrett p_iSpieleBrett )
    {
        // @todo das muss über den Websocket oder REST Class gefüllt werden
    }

}
