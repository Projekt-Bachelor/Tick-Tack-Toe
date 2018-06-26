package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.controller;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.ESpiele;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.ISpieleBrett;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.player.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang.math.RandomUtils.nextInt;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST Call für ein Spielebrett
 *
 * @todo hier müsen verschiedene Aufrufen hinein, um z.B. ein Brett mit einem Human-Player, Random-Bot und Min-Max-Bot zu erzuegen
 */
@RestController
@RequestMapping("/spielebrett")
public class CSpielebrettController {

    /**
     * erzeugt einen neues Brett
     *
     * @param p_name Name des Bretts
     *               Überarbeitung um den Typ mitzübergeben
     */
    //    @RequestMapping(value = "/create/{name}/{width}/{height}")
    @RequestMapping(value = "/create/{name}/{type}/{width}/{height}")
    public ISpieleBrett  create(@PathVariable("name") final String p_name, @PathVariable("type") final String p_type, @PathVariable("width") final int p_width, @PathVariable("height") final int p_height) {
        ESpiele.INSTANCE.generate(p_name, p_width, p_height);
        System.out.println("Test");
        switch (p_type) {
            case "PvP":
                //gameName(name_counter, name);
                System.out.println("PvP wurde ausgewählt.");
                break;

            case "einfach":
                //gameName(name_counter, name);
                //final EItem p_value = EItem.KREIS;
                new CRandomBot();
                System.out.println("Einfach wurde ausgewählt.");
                break;

            case "mittel":
                //gameName(name_counter, name);
                new CMediumBot();
                System.out.println("Mittel wurde ausgewählt.");
                break;

            case "schwer":
                //gameName(name_counter, name);
                new CMinMaxBot();
                System.out.println("Schwer wurde ausgewählt.");
                break;
            default:
                System.out.println("Es wurde kein gültiger Bot ausgewählt... Fehler bei der Zuweisung!");
        }
        return ESpiele.INSTANCE.apply(p_name);
    }

    /**
     * liefert das Spielebrett Objekt
     *
     * @param p_name Name des Spielebretts
     * @return Spielebrett
     */
    @RequestMapping(value = "/{name}/show", produces = APPLICATION_JSON_VALUE)
    public ISpieleBrett players(@PathVariable("name") final String p_name) {
        return ESpiele.INSTANCE.apply(p_name);
    }

    @RequestMapping(value = "/{name}/set-mark/{x}/{y}")
    public ISpieleBrett set(@PathVariable("name") final String p_name, @PathVariable("x") final int p_x, @PathVariable("y") final int p_y) {
        ISpieleBrett brett = ESpiele.INSTANCE.apply(p_name);
        CHuman player = new CHuman();
        CRandomBot bot = new CRandomBot();
        player.accept(brett, p_x, p_y);
        //bot.accept(brett);
        System.out.println(brett);
        return brett;
    }
}