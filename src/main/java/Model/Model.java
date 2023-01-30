package Model;

//import javax.sound.midi.*;
import java.util.*;
import java.math.*;

public class Model {
    public final int numOfColumns;
    public final int numOfRows;
    public int numOfErrors, currErrors;
    private final List<Row> rows;
    private List<Cell> task, lastTask;
    private final List<GameListener> listeners;
    private final int[] instruments = {50,60,70,72,64,56,58,47,67,63};

    public Cell getCell(int row, int column){
        return rows.get(row).getCell(column);
    }

    public Model(int rowsNum, int columnsNum){
        this.numOfRows = rowsNum;
        this.numOfColumns = columnsNum;
        rows = new ArrayList<Row>(0);
        for(int i = 0; i < numOfRows; i++){
            rows.add(new Row(i, numOfColumns));
        }
        listeners = new ArrayList<>(0);
    }
    public Model(){
        this(5, 5);
    }
    public Model(int numOfRowsAndColumns){
        this(numOfRowsAndColumns, numOfRowsAndColumns);
    }
    public void addListener(GameListener listener){
        listeners.add(listener);
    }


    public boolean taskActive(){
        if(task == null) return false;
        return task.size() > 0;
    }
    public void createTask(int numberOfCells, int numberOfErrors){
        this.numOfErrors = numberOfErrors;
        currErrors = 0;
        task = new ArrayList<Cell>(0);
        for(int i = 0; i < numberOfCells; i++){
            task.add(getCell
                    ((int) (Math.random()*numOfRows), (int) (Math.random()*numOfColumns)));
        }
        List<Cell> taskCopy = new ArrayList<>(task);
        lastTask = new ArrayList<>(task);
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
        currErrors++;
        if(currErrors > numOfErrors && numOfErrors != -1)
        {
            task = null;
            for(GameListener listener: listeners){
                listener.taskFailed(cell);
            }
            return;
        }

        for(GameListener listener: listeners){
            listener.taskError(currErrors, cell);
        }
    }

    public void restartTask(){
        currErrors = 0;
        task = new ArrayList<>(lastTask);
        List<Cell> taskCopy = new ArrayList<>(task);
        for(GameListener listener: listeners){
            listener.taskCreated(taskCopy);
        }
    }
    /*
    public void createTaskSeries(int startNumber){
        createTask(startNumber);
    }

     */
}
