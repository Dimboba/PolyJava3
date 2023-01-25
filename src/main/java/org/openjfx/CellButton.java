package org.openjfx;

import Controller.BoardListener;
import Controller.CellListener;
import Model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Timer;
import java.util.TimerTask;

public class CellButton extends Button {

    private Cell cell;
    private CellListener listener;
    private Model model;
    private String baseStyle;
    public CellButton(Cell cell, Model model, CellListener listener){
        super();
        this.cell = cell;
        this.model = model;
        this.listener = listener;
        this.setOnAction(e -> {
            //System.out.println("MouseClicked");
            listener.cellClicked(cell);
        });
        String previousStyle = this.getStyle();
    }

    public void paint(boolean mistake){
        //System.out.println(mistake);
        String previousStyle = this.getStyle();
        this.setText("Click!");
        if(mistake)
            this.setStyle("-fx-background-color: #ff0000; ");
        else
            this.setStyle("-fx-background-color: #05d321; ");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {

                Platform.runLater(() -> {
                    CellButton.this.setStyle(baseStyle);
                    CellButton.this.setText("");
                });
                timer.cancel();
                timer.purge();
            }
        }, 500);
    }

}
