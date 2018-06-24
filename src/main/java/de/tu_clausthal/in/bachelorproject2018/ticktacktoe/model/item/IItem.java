package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item;

public interface IItem
{
    /**
     * liefert die X Position
     *
     * @return X-Position
     */
    int x();

    /**
     * liefert die Y Position
     *
     * @return Y-Position
     */
    int y();

    /**
     * liefert den Wert des Items zurück
     *
     * @return Wert des Items
     */
    boolean item();
}
