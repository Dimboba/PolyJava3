package org.openjfx;

import Controller.CellListener;
import Model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.HashMap;

public class Board extends GridPane{

    private final HashMap<Cell, CellButton> cellButtonMap;
    public Board(Model model, CellListener listener) {
        cellButtonMap = new HashMap<>();

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.CENTER);
        for (int i = 0; i < model.numOfRows; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100d / model.numOfRows);
            this.getRowConstraints().add(row);
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100d / model.numOfColumns);
            this.getColumnConstraints().add(col);
            for (int j = 0; j < model.numOfColumns; j++) {
                Cell currCell = model.getCell(i, j);
                CellButton btn = new CellButton(currCell, model, listener);
                cellButtonMap.put(currCell, btn);
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                this.add(btn, j, i);
            }
        }
    }

    public CellButton getCellButton(Cell cell){ return cellButtonMap.get(cell); }
}
