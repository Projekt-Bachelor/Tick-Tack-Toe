package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item;

/**
 * Klsse für ein Item im Spielefeld
 */
public final class CItem implements IItem
{
    /**
     * X-Position
     */
    private final int x;
    /**
     * Y-Position
     */
    private final int y;
    /**
     * Enum ob es Kreis oder Kreuz ist
     */
    private final EItem m_item;

    /**
     * Konstruktor
     *
     * @param p_item Enum für die Art des Objektes
     * @param p_x X-Position
     * @param p_y Y-Position
     */
    public CItem( final EItem p_item, final int p_x, final int p_y )
    {
        m_item = p_item;
        x = p_x;
        y = p_y;
    }

    @Override
    public final int x()
    {
        return x;
    }

    @Override
    public final int y()
    {
        return y;
    }

}
