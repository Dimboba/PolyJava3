package org.openjfx;

import Controller.BoardListener;
import Controller.CellListener;
import Model.*;
import javafx.scene.control.Button;

public class CellButton extends Button {

    private Cell cell;
    private CellListener listener;
    //private Model model;
    public CellButton(Cell cell, CellListener listener){
        super();
        this.cell = cell;
        this.listener = listener;
        //this.setOnAction(); !!!!
    }

}
