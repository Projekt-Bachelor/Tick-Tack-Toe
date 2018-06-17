package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.controller;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.ESpiele;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * REST Controller für die Spielebretter
 */
@RestController
@RequestMapping( "/spiele" )
public final class CSpieleController
{

    /**
     * Aufruf um eine Liste aller Spielnamen zu erhalten
     *
     * @return Tischliste
     */
    @RequestMapping( value = "/list", produces = APPLICATION_JSON_VALUE )
    public Set<String> list()
    {
        return ESpiele.INSTANCE.get();
    }

}
