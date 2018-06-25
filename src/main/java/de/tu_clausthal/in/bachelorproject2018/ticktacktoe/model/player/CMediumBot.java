package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.player;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.ISpieleBrett;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.EItem;

import java.util.Random;


/**
 * Klasse des Medium Bots
 */
public class CMediumBot extends IBasePlayer
{
    /**
     * Zufallsgenerator für den Medium Bot
     */
    private final Random m_random = new Random();

    /**
     * Konstruktor
     *
     * @param p_value Item (Kreuz / Kreis), das durch den Spieler verwendet wird
     */
    public CMediumBot( final EItem p_value ) { super( "Medium-Bot", p_value ); }

    @Override
    public void accept(ISpieleBrett p_brett) {
        /*
        int i;
        i = m_random.nextInt(11);
        if(i <= 7){

        }
        else{

        }
        */
    }
}
