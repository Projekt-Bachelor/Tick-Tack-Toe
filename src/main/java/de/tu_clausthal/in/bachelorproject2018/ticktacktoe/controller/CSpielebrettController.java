package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.controller;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.ESpiele;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.ISpieleBrett;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.IItem;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.player.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

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
        switch (p_type) {
            case "PvP":
                ESpiele.INSTANCE.generate(p_name, p_width, p_height, 0);
                //gameName(name_counter, name);
                System.out.println("PvP wurde ausgewählt.");
                break;

            case "einfach":
                ESpiele.INSTANCE.generate(p_name, p_width, p_height, 1);
                //gameName(name_counter, name);
                //final EItem p_value = EItem.KREIS;
                System.out.println("Einfach wurde ausgewählt.");
                break;

            case "mittel":
                ESpiele.INSTANCE.generate(p_name, p_width, p_height, 2);
                //gameName(name_counter, name);
                System.out.println("Mittel wurde ausgewählt.");
                break;

            case "schwer":
                ESpiele.INSTANCE.generate(p_name, p_width, p_height, 3);
                //gameName(name_counter, name);
                System.out.println("Schwer wurde ausgewählt.");
                break;
            case "unmöglich":
                ESpiele.INSTANCE.generate(p_name, p_width, p_height, 4);
                //gameName(name_counter, name);
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

        IItem won;
        boolean draw = false;
        AtomicReference<IItem>[][] elements;
        CHuman player = new CHuman();

        int difficulty = ESpiele.INSTANCE.apply(p_name).getM_difficulty();

        switch(difficulty){
            case 0:
                break;
            case 1:
                CRandomBot bot1 = new CRandomBot();

                if(!player.accept(ESpiele.INSTANCE.apply(p_name), p_x, p_y)){
                    return ESpiele.INSTANCE.apply(p_name);
                }
                won = ESpiele.INSTANCE.apply(p_name).checkWin();
                if(won == null) {
                    draw = ESpiele.INSTANCE.apply(p_name).checkDraw();
                }
                if(won == null || draw == false) {
                    //bot turn
                    bot1.accept(ESpiele.INSTANCE.apply(p_name));
                    won = ESpiele.INSTANCE.apply(p_name).checkWin();
                    if (won == null) {
                        draw = ESpiele.INSTANCE.apply(p_name).checkDraw();
                    }
                }
                return ESpiele.INSTANCE.apply(p_name);
            case 2:
                CMediumBot bot2 = new CMediumBot();
                if(!player.accept(ESpiele.INSTANCE.apply(p_name), p_x, p_y)){
                    return ESpiele.INSTANCE.apply(p_name);
                }
                won = ESpiele.INSTANCE.apply(p_name).checkWin();
                if(won == null) {
                    draw = ESpiele.INSTANCE.apply(p_name).checkDraw();
                }
                if(won == null || draw == false) {
                    //bot turn
                    bot2.accept(ESpiele.INSTANCE.apply(p_name));
                    won = ESpiele.INSTANCE.apply(p_name).checkWin();
                    if (won == null) {
                        draw = ESpiele.INSTANCE.apply(p_name).checkDraw();
                    }
                }
                return ESpiele.INSTANCE.apply(p_name);
            case 3:
                CHardBot bot3 = new CHardBot();
                if(!player.accept(ESpiele.INSTANCE.apply(p_name), p_x, p_y)){
                    return ESpiele.INSTANCE.apply(p_name);
                }
                won = ESpiele.INSTANCE.apply(p_name).checkWin();
                if(won == null) {
                    draw = ESpiele.INSTANCE.apply(p_name).checkDraw();
                }
                if(won == null || draw == false) {
                    //bot turn
                    bot3.accept(ESpiele.INSTANCE.apply(p_name));
                    won = ESpiele.INSTANCE.apply(p_name).checkWin();
                    if (won == null) {
                        draw = ESpiele.INSTANCE.apply(p_name).checkDraw();
                    }
                }
                return ESpiele.INSTANCE.apply(p_name);
            case 4:
                CMinMaxBot bot4 = new CMinMaxBot();
                if(!player.accept(ESpiele.INSTANCE.apply(p_name), p_x, p_y)){
                    return ESpiele.INSTANCE.apply(p_name);
                }
                won = ESpiele.INSTANCE.apply(p_name).checkWin();
                if(won == null) {
                    draw = ESpiele.INSTANCE.apply(p_name).checkDraw();
                }
                if(won == null || draw == false) {
                    //bot turn
                    bot4.accept(ESpiele.INSTANCE.apply(p_name));
                    won = ESpiele.INSTANCE.apply(p_name).checkWin();
                    if (won == null) {
                        draw = ESpiele.INSTANCE.apply(p_name).checkDraw();
                    }
                }
                return ESpiele.INSTANCE.apply(p_name);
            default:
                System.out.println("error");
        }

        return ESpiele.INSTANCE.apply(p_name);

    }
}