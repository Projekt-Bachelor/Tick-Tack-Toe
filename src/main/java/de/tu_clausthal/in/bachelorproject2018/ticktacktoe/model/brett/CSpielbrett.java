package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.ESpiele;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.EItem;
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
     *
     */
    @JsonProperty( "winner" )
    private final EItem m_winner;

    /**
     *
     */
    @JsonProperty( "draw" )
    private final boolean m_draw;

    /**
     *
     */
    @JsonProperty( "difficulty" )
    private final int m_difficulty;

    public int getM_difficulty() {
        return m_difficulty;
    }

    public AtomicReference<IItem>[][] getM_elements() {
        return m_elements;
    }

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
    public CSpielbrett( final String p_name, final int p_width, final int p_height, final int p_difficulty )
    {
        m_name = p_name;
        m_width = p_width;
        m_height = p_height;
        m_winner = null;
        m_draw = false;
        m_difficulty = p_difficulty;


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
    public boolean set(final IItem p_item )
    {
        /*
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
    */
        return m_elements[p_item.getX()][p_item.getY()].compareAndSet( null, p_item );
    }

    public IItem checkWin() {
        IItem won;

        if (hasEqualValue(m_elements[0][0], m_elements[1][1], m_elements[2][2])) {
            won = m_elements[0][0].get();
        } else if (hasEqualValue(m_elements[2][0], m_elements[1][1], m_elements[0][2])) {
            won = m_elements[2][0].get();
        } else if (hasEqualValue(m_elements[1][0], m_elements[1][1], m_elements[1][2])) {
            won = m_elements[1][0].get();
        } else if (hasEqualValue(m_elements[0][0], m_elements[0][1], m_elements[0][2])) {
            won = m_elements[0][0].get();
        } else if (hasEqualValue(m_elements[1][0], m_elements[1][1], m_elements[1][2])) {
            won = m_elements[1][0].get();
        } else if (hasEqualValue(m_elements[2][0], m_elements[2][1], m_elements[2][2])) {
            won = m_elements[2][0].get();
        } else if (hasEqualValue(m_elements[0][0], m_elements[1][0], m_elements[2][0])) {
            won = m_elements[0][0].get();
        } else if (hasEqualValue(m_elements[0][1], m_elements[1][1], m_elements[2][1])) {
            won = m_elements[0][1].get();
        } else if (hasEqualValue(m_elements[0][2], m_elements[1][2], m_elements[2][2])) {
            won = m_elements[0][2].get();
        }
        else {
            won = null;
        }

        if(won != null){
            System.out.println("erkannt");
            //ESpiele.INSTANCE.remove(this);
        }
        return won;
    }

    public boolean checkDraw()
    {
        boolean draw = true;
        for(AtomicReference<IItem>[] line: m_elements){
            for(AtomicReference<IItem> field: line){
                if(field.get() == null){
                    draw = false;
                }
            }
        }
        if(draw){
            ESpiele.INSTANCE.remove(this);
        }
        return draw;
    }

    private boolean hasEqualValue(AtomicReference<IItem> element1, AtomicReference<IItem> element2, AtomicReference<IItem> element3)
    {

        if( element1.get() != null && element2.get() != null && element3.get() != null) {
            if (element1.get().getM_item() == element2.get().getM_item() && element1.get().getM_item() == element3.get().getM_item()) {
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    }
}