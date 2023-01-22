package Model;

import javax.sound.midi.*;

public class Cell {
    private int instrument;
    private int note;

    public Cell(int instrument, int note) {
        this.instrument = instrument;
        this.note = note;
    }
}