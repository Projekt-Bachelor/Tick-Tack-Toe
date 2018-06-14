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
    public CRandomBot( final EItem p_value )
    {
        super( "Random-Bot", p_value );
    }

    @Override
    public void accept( final ISpieleBrett p_brett )
    {
        // Methode, die das setzen übernimmt
        int x = m_random.nextInt( p_brett.width() );
        int y = m_random.nextInt( p_brett.height() );
        while ( !p_brett.set( m_value.apply( x, y ) ) )
        {
            x = m_random.nextInt( p_brett.width() );
            y = m_random.nextInt( p_brett.height() );
        }
    }

}
