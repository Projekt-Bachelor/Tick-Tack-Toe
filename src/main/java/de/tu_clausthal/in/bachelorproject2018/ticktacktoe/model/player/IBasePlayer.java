package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.player;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.EItem;

import java.util.Objects;


/**
 * abstrakte Klasse eines Spielers, der den Namen und das Item, das er setzen kann enthält
 */
public abstract class IBasePlayer implements IPlayer
{
    /**
     * Name des Spielers
     */
    private final String m_name;
    /**
     * Item was der Spieler setzt (Kreuz / Kreis)
     */
    protected final EItem m_value;

    /**
     * Konstruktor, der nur in abgeleiteten Klassen verwendet werden kann
     * @param p_name Name des Spielers
     * @param p_value Item (Kreuz / Kreis), das durch den Spieler verwendet wird
     */
    protected IBasePlayer( final String p_name, final EItem p_value )
    {
        m_name = p_name;
        m_value = p_value;
    }

    @Override
    public final String name()
    {
        return m_name;
    }


    @Override
    public int hashCode()
    {
        return this.name().hashCode();
    }

    @Override
    public boolean equals( final Object p_object )
    {
        return Objects.nonNull( p_object ) && p_object instanceof IPlayer && p_object.hashCode() == this.hashCode();
    }
}
