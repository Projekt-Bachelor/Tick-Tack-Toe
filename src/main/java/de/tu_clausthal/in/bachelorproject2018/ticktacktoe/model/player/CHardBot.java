package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.player;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.ISpieleBrett;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.EItem;

import java.util.Random;

public class CHardBot extends IBasePlayer
{
    /**
     * Zufallsgenerator für den Hard Bot
     */
    private final Random m_random = new Random();
    private final CRandomBot m_RandomBot = new CRandomBot();
    private final CMinMaxBot m_MinMaxBot = new CMinMaxBot();

    /**
     * Konstruktor
     */
    public CHardBot() { super( "Hard-Bot", EItem.KREIS ); }

    @Override
    public void accept(ISpieleBrett p_brett) {
        int i;
        i = m_random.nextInt(11);
        if(i <= 8){
            m_MinMaxBot.accept(p_brett);
        }
        else{
            m_RandomBot.accept(p_brett);
        }
    }
}
