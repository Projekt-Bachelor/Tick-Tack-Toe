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
     */
    public CHuman()
    {
        super( "Human", EItem.KREUZ );
    }

    @Override
    public void accept( final ISpieleBrett p_brett )
    {
        // @todo das muss über den Websocket oder REST Class gefüllt werden
    }

    public void accept(final ISpieleBrett p_brett, int x, int y )
    {
        // @todo das muss über den Websocket oder REST Class gefüllt werden
        boolean[] set_won_draw;
        set_won_draw = p_brett.set( m_value.apply( x, y ) );
        while ( !set_won_draw[0] ) {
            set_won_draw = p_brett.set( m_value.apply( x, y ) );
        }
    }

}
