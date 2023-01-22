package Model;

import javax.sound.midi.*;
import java.util.*;
import java.math.*;

public class Model {
    private final int cellsInRow = 5;
    private final int numOfRows = 5;
    private final List<Row> rows;
    private List<Cell> task;
    private final List<GameListener> listeners;
    private final int instruments[] = {50,60,70,72,64,56,58,47,67,63};

    private Cell getCell(int row, int cellNum){
        return rows.get(row).getCell(cellNum);
    }
    public Model(){
        rows = new ArrayList<Row>(0);
        for(int i = 0; i < numOfRows; i++){
            rows.add(new Row(cellsInRow, instruments[i]));
        }
        listeners = new ArrayList<>(0);
    }

    public void addListener(GameListener listener){
        listeners.add(listener);
    }
    public void createTask(int numberOfNotes){
        task = new ArrayList<Cell>(0);
        for(int i = 0; i < numberOfNotes; i++){
            task.add(getCell
                    ((int) (Math.random()*numOfRows), (int) (Math.random()*cellsInRow)));
        }
        //task = Collections.unmodifiableList(task);
    }
    public void completeTask(int row, int cellNum){
        if(task.get(task.size() - 1) == this.getCell(row, cellNum)) {
            task.remove(task.size() - 1);
            for(GameListener listener: listeners){
                listener.taskCompleted(task.size(), row, cellNum);
            }
        }
        for(GameListener listener: listeners){
            listener.taskCompleted(-1, row, cellNum);
        }
    }

    public void playSound(int row, int cellNum){

    }
}
