package Model;

import java.util.*;

public class Row {
    private List<Cell> row = new ArrayList<>();
    public Row(int numOfCells, int instrument){
        int minNote = 30, maxNote = 120;
        for(int i = 0; i < numOfCells; i++){
            row.add(new Cell(instrument,
                    minNote + ((maxNote - minNote) / numOfCells) * i));
        }

    }
}
