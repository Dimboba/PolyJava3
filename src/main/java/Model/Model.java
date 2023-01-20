package Model;

import javax.sound.midi.*;
import java.util.*;

public class Model {
    private final int cellsInRow = 5;
    private final int numOfRows = 5;
    private List<Row> rows;

    private final int instruments[] = {50,60,70,72,64,56,58,47,67,63};

    public Model(){

        for(int i = 0; i < numOfRows; i++){
            rows.add(new Row(cellsInRow, instruments[i]));
        }
    }

    public void playSound(int row, int cell){

    }
}
