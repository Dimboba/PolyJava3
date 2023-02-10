package org.openjfx;

import Controller.CellListener;
import Model.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

//import javax.print.attribute.standard.Media;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CellButton extends Button {

    private Cell cell;
    private CellListener listener;
    private Model model;
    private MainApp mainApp;
    private String baseStyle;
    public CellButton(Cell cell, Model model, CellListener listener, MainApp mainApp){
        super();
        this.cell = cell;
        this.model = model;
        this.listener = listener;
        this.mainApp = mainApp;
        this.setOnAction(e -> {
            //System.out.println("MouseClicked");
            listener.cellClicked(cell);
        });
        String previousStyle = this.getStyle();
    }

    public void paintAndPlay(boolean mistake){
        this.paint(mistake);
        this.playSound(mistake);
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

    public void playSound(boolean mistake){
        if(!mainApp.isSoundOn()) return;
        String soundName;
        boolean active = model.taskActive();
        if(mistake)
        {
            if(active) soundName = "/Error.wav";
            else soundName = "/Lose.wav";
        }
        else {
            if(active) return;
            else soundName = "/Win.wav";
        }
        Media media = null;
        try {
            media = new Media(getClass().getResource(soundName).toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

}
