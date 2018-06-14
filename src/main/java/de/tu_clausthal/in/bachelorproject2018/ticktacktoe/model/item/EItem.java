package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item;

import java.text.MessageFormat;
import java.util.function.BiFunction;


/**
 * Factory für ein Item (Kreuz / Kreis Objekt).
 * Der Enum erbt auch von BiFunction, d.h. 2 Int-Werte (X & Y) werden rein gegeben und
 * ein neues Objekt wird erzeugt
 */
public enum EItem implements BiFunction<Integer, Integer, IItem>
{
    KREIS,
    KREUZ;

    /**
     * erzeugt ein neues Objekt (Factory)
     *
     * @param p_x X-Position
     * @param p_y Y-Position
     * @return Objekt
     */
    @Override
    public IItem apply( final Integer p_x, final Integer p_y )
    {
        switch ( this )
        {
            case KREIS:
                return new CItem( EItem.KREIS, p_x, p_y );

            case KREUZ:
                return new CItem( EItem.KREUZ, p_x, p_y );

            default:
                throw new RuntimeException( MessageFormat.format( "Element [{0}] unbekannt", this ) );
        }
    }
}
