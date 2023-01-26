package Model;

import javax.sound.midi.*;
import java.util.Objects;

public class Cell {
    private int column;
    private int row;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass() || obj.hashCode() != hashCode()) return false;
        Cell cell = (Cell) obj;
        return column == cell.column && row == cell.row;
    }

    @Override
    public int hashCode() {
        int res = 11;
        res = res * 19 + row;
        return res * 19 + column;
    }
}