package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.controller;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.ESpiele;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.ISpieleBrett;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     */
    @RequestMapping(value = "/create/{name}/{width}/{height}")
    public ISpieleBrett create(@PathVariable("name") final String p_name, @PathVariable("width") final int p_width, @PathVariable("height") final int p_height) {
        ESpiele.INSTANCE.generate(p_name, p_width, p_height);
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
    public ESpiele set(@PathVariable("name") final String p_name, @PathVariable("x") final int p_x, @PathVariable("y") final int p_y) {
        return ESpiele.INSTANCE.set(p_name, p_x, p_y);
    }

}
