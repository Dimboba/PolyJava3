package Model;

import java.util.*;

public class Row {
    private List<Cell> cells = new ArrayList<>();
    protected Cell getCell(int column){
        return cells.get(column);
    }
    public Row(int row, int numOfColumns){
        int minNote = 30, maxNote = 120;
        for(int i = 0; i < numOfColumns; i++){
            cells.add(new Cell(row, i));
        }

    }
}
