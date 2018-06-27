package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.controller;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.IItem;

import java.util.concurrent.atomic.AtomicReference;

public class CSetReturn {

    private final AtomicReference<IItem>[][] elements;
    private final boolean won;
    private final boolean draw;

    public AtomicReference<IItem>[][] getElements() {
        return elements;
    }

    public boolean isWon() {
        return won;
    }

    public boolean isDraw() {
        return draw;
    }

    public CSetReturn(AtomicReference<IItem>[][] elements, boolean won, boolean draw){

        this.elements = elements;
        this.won = won;
        this.draw = draw;
    }
}
