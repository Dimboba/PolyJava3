package Model;

//import javax.sound.midi.*;
import java.util.*;
import java.math.*;

public class Model {
    public final int numOfColumns = 5;
    public final int numOfRows = 5;
    private final List<Row> rows;
    private List<Cell> task;
    private final List<GameListener> listeners;
    private final int[] instruments = {50,60,70,72,64,56,58,47,67,63};

    public Cell getCell(int row, int column){
        return rows.get(row).getCell(column);
    }

    public Model(int numOfRows, int numOfColumns){
        rows = new ArrayList<Row>(0);
        for(int i = 0; i < numOfRows; i++){
            rows.add(new Row(i, numOfColumns));
        }
        listeners = new ArrayList<>(0);
    }
    public Model(){
        this(5, 5);
    }
    public void addListener(GameListener listener){
        listeners.add(listener);
    }


    public boolean taskActive(){
        if(task == null) return false;
        return task.size() > 0;
    }
    public void createTask(int numberOfCells){
        task = new ArrayList<Cell>(0);
        for(int i = 0; i < numberOfCells; i++){
            task.add(getCell
                    ((int) (Math.random()*numOfRows), (int) (Math.random()*numOfColumns)));
        }
        List<Cell> taskCopy = new ArrayList<Cell>(task);
        for(GameListener listener: listeners){
            listener.taskCreated(taskCopy);
        }
    }
    public void completeTask(Cell cell){
        if(task.get(task.size() - 1) == cell) {
            task.remove(task.size() - 1);
            for(GameListener listener: listeners){
                listener.taskCompleted(task.size(), cell);
            }
            return;
        }
        for(GameListener listener: listeners){
            listener.taskCompleted(-1, cell);
        }
    }
    public void createTaskSeries(int startNumber){
        createTask(startNumber);
    }
}
