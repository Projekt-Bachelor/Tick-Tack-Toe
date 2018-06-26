package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.player;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.ISpieleBrett;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.EItem;

import java.util.Random;


/**
 * Klasse des Random Bots
 */
public class CRandomBot extends IBasePlayer
{
    /**
     * der Random-Bot bekommt einen eigenen Zufallszahlengenerator
     */
    private final Random m_random = new Random();

    /**
     * Konstruktor
     *
     * @param p_value Item (Kreuz / Kreis), das durch den Spieler verwendet wird
     */
    public CRandomBot()
    {
        super( "Random-Bot", EItem.KREIS );
    }

    @Override
    public void accept(final ISpieleBrett p_brett)
    {
        // Methode, die das setzen übernimmt
        int x;
        int y;
        x = m_random.nextInt( p_brett.width() );
        y = m_random.nextInt( p_brett.height() );
        boolean[] set_won_draw;
        set_won_draw = p_brett.set( m_value.apply( x, y ) );
        while ( !set_won_draw[0] ) {
            x = m_random.nextInt( p_brett.width() );
            y = m_random.nextInt( p_brett.height() );
            set_won_draw = p_brett.set( m_value.apply( x, y ) );
        }

    }
}
