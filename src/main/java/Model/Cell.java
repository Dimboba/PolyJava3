package Model;

import javax.sound.midi.*;

public class Cell {
    private int column;
    private int row;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }
}