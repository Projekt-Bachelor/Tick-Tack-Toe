package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.IItem;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;


/**
 * Spielebrett
 *
 * @todo das Spielebrett muss erweitert werden, so dass eben die beiden Spielerobjekte (Human, Min-Max-Bot, Random-Bot) Teil dieser Klasse werden
 * @todo die Spielelogik muss hier hinein, wann ein Spieler gewonnen hat
 */
public final class CSpielbrett implements ISpieleBrett
{
    /**
     * Breite des Spielebretts
     */
    @JsonProperty( "width" )
    private final int m_width;
    /**
     * Höhe des Spielebretts
     */
    @JsonProperty( "height" )
    private final int m_height;
    /**
     * Name des Spielebrett / des Spiels
     */
    @JsonProperty( "name" )
    private final String m_name;
    /**
     * ein doppeltes Array von AtomicReferences von IItems,
     * das Array entspricht dem Brett, jedes Element ist eine AtomicReferenz, weil
     * in einer Webapplikation zwei Threads (wenn zwei Anfragen vom Browser kommen) gleichzeitig
     * auf eine Zelle zugreifen könnten, darum muss es thread-sicher sein, Java kennt dafür die
     * Atomic-Datentypen wie AtomicInteger, AtomicBoolean und AtomicReferenz kann eben das übergebene
     * Element enthalten, hier IITem, d.h. einen Kreis oder ein Kreuz
     */
    @JsonProperty( "elements" )
    private final AtomicReference<IItem>[][] m_elements;

    /**
     * Konstruktor.
     * Da bei der Array-Initialisierung des AtomicReference Arrays der Generic-Parameter nicht mitgegeben werden kann
     * und dies zu einem Compilerfehler führt, wird mittels @SuppressWarnings diese Fehler unterdrückt, aber das sollte
     * man nur in begründeten Ausnahmefällen machen
     *
     * @param p_name Name des Spiels / Spielebretts
     * @param p_width Anzahl der Spalten
     * @param p_height Anzahl der Zeilen
     */
    @SuppressWarnings( {"unchecked", "rawtypes"} )
    public CSpielbrett( final String p_name, final int p_width, final int p_height )
    {
        m_name = p_name;
        m_width = p_width;
        m_height = p_height;

        // erzeuge Array (die Zeilen geben die Y Koordinate an, die Spalten die X Koordinate)
        m_elements = new AtomicReference[m_height][m_width];

        // Array Element Initialisierung mittels Streams
        IntStream.range( 0, m_width )
                 .boxed()
                 .forEach( x -> IntStream.range( 0, m_height )
                                         .boxed()
                                         // setze in jedes Array Element eine leere Referenz
                                         .forEach( y -> m_elements[x][y] = new AtomicReference<>() )
                 );
    }

    @Override
    public String name()
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
        return Objects.nonNull( p_object ) && p_object instanceof ISpieleBrett && p_object.hashCode() == this.hashCode();
    }

    @Override
    public int width()
    {
        return m_width;
    }

    @Override
    public int height()
    {
        return m_height;
    }

    @Override
    public boolean isempty( final int p_x, final int p_y )
    {
        return Objects.isNull( m_elements[p_x][p_y].get() );
    }

    @Override
    public boolean set( final IItem p_item )
    {
        // @todo hier kann z.B. die Logik hin um zu überprüfen, wer gewonnen hat
        // @todo wenn ein Gewinner feststeht, dann muss danach das Spielebrett-Objekt aus dem Enum ESpiele mittels remove entfernt werden
        return m_elements[p_item.y()][p_item.y()].compareAndSet( null, p_item );
    }

}
