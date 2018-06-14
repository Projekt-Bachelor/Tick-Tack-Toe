package de.tu_clausthal.in.bachelorproject2018.ticktacktoe;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.Spiel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.Spiel.Anfaenger;
import static de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.Spiel.Runde;
import static de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.Spiel.neuesFeld;

/**
 * Applikation
 */
@SpringBootApplication
@EnableAutoConfiguration
public class CApplication {
    /**
     * main
     *
     * @param p_args arguments
     */
    public static void main(final String[] p_args) {
        SpringApplication.run(CApplication.class, p_args);
        if(Runde == false){
            //neuesSpiel();
            neuesFeld();
            Anfaenger = true;
        }
        else {
            System.out.println("Es wurde kein Spielstein gesetzt! Setzten sie bitte zum Vortfahren einen Spielstein");
            int x = -1;
            int y=-1;
            Spiel.setzen(x, y);// Benutzer setzt einen Stein
        }
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute("spiel") Spiel spiel) {
        return "index";
    }

}