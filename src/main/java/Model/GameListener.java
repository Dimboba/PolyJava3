package Model;


public interface GameListener{
    void taskCompleted(int left, int lastRow, int lastCellNum);
    void taskCreated();
}
