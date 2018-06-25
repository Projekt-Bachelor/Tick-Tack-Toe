package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.CSpielbrett;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.ISpieleBrett;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.apache.commons.lang.math.RandomUtils.nextInt;


/**
 * Singleton mit allen Spielen
 */
public enum ESpiele implements Function<String, ISpieleBrett>, Supplier<Set<String>> {
    INSTANCE;

    /**
     * eine thread-sichere Map, um mehrere Spielebretter zu verwalten
     */
    private final Map<String, ISpieleBrett> m_bretter = new ConcurrentHashMap<>();

    /**
     * erzeugt ein neues Spielebrett
     *
     * @param p_name   Name des Spiels
     * @param p_width  Breite des Bretts
     * @param p_height Höhe des Bretts
     */
    public void generate(final String p_name, final int p_width, final int p_height) {
        if (m_bretter.containsKey(p_name))
            throw new RuntimeException(MessageFormat.format("Spiel [{0}] existiert bereits", p_name));

        m_bretter.put(p_name, new CSpielbrett(p_name, p_width, p_height));
    }

    /**
     * entfernt ein Spielebrett
     *
     * @param p_brett brett
     */
    public void remove(final ISpieleBrett p_brett) {
        m_bretter.remove(p_brett.name());
    }

    @Override
    public ISpieleBrett apply(final String p_name) {
        final ISpieleBrett l_brett = m_bretter.get(p_name);
        if (Objects.isNull(l_brett))
            throw new RuntimeException(MessageFormat.format("Spielebrett mit dem Namen [{0}] nicht gefunden", p_name));

        return l_brett;
    }

    @Override
    public Set<String> get() {
        return m_bretter.keySet();
    }

    /**
     * @param p_x
     * @param p_y
     * @TODO: 17.06.18Die Funktion muss einen Stein setzten an der Stelle x, y. Referenz zu dem Controller
     */
    public ESpiele set(final String p_name, int p_x, int p_y) {

        return null;
    }
}
