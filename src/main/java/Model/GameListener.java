package Model;


import java.util.List;

public interface GameListener{
    void taskCompleted(int left, Cell cell);
    void taskCreated(List<Cell> taskCopy);
}
