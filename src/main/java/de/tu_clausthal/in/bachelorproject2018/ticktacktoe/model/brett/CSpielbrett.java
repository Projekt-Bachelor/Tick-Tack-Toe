package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.ESpiele;
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
    public String type() {
        return null;
    }

    @Override
    public boolean isempty( final int p_x, final int p_y )
    {
        return Objects.isNull( m_elements[p_x][p_y].get() );
    }


    @Override
    public boolean[] set(final IItem p_item )
    {
        boolean[] set_won_draw = {false, false, false};
        set_won_draw[0] = m_elements[p_item.x()][p_item.y()].compareAndSet( null, p_item );

        // Logik um zu überprüfen, wer gewonnen hat
        if(set_won_draw[0]) {
            set_won_draw[1] = won(p_item, m_elements);
            if(!set_won_draw[1]){
                set_won_draw[2] = draw(m_elements);
            }
        }

        // @todo wenn ein Gewinner feststeht, dann muss danach das Spielebrett-Objekt aus dem Enum ESpiele mittels remove entfernt werden
        if((set_won_draw[1] == true || set_won_draw[2] == true)){
            ESpiele.INSTANCE.remove(this);
        }

        return set_won_draw;
    }

    public boolean won(final IItem p_item, AtomicReference<IItem>[][] elements)
    {
        boolean won;
        int x = p_item.x();
        int y = p_item.y();

        if(hasEqualValue(elements[0][0], elements[1][1], elements[2][2])){
            won = true;
        }
        else if(hasEqualValue(elements[2][0], elements[1][1], elements[0][2])){
            won = true;
        }
        else if(hasEqualValue(elements[x][0], elements[x][1], elements[x][2])){
            won = true;
        }
        else if(hasEqualValue(elements[0][y], elements[1][y], elements[2][y])){
            won = true;
        }
        else{
            won = false;
        }
        return won;
    }

    public boolean draw(AtomicReference<IItem>[][] elements)
    {
        boolean draw = true;
        for(AtomicReference<IItem>[] line: elements){
            for(AtomicReference<IItem> field: line){
                if(field.get() == null){
                    draw = false;
                }
            }
        }
        return draw;
    }

    public boolean hasEqualValue(AtomicReference<IItem> element1, AtomicReference<IItem> element2, AtomicReference<IItem> element3)
    {
        if(element1.get().item() == element2.get().item() && element1.get().item() == element3.get().item()) {
            return true;
        }
        else{
            return false;
        }
    }

}