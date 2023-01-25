package Controller;

import Model.*;

public class BoardListener implements CellListener{
    private final Model model;

    public BoardListener(Model model){
        this.model = model;
    }

    @Override
    public void cellClicked(Cell cell) {
        if(model.taskActive())
            model.completeTask(cell);
    }
}
