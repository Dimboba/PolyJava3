package org.openjfx;

import Controller.BoardListener;
import Model.Cell;
import Model.GameListener;
import Model.Model;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class MainApp extends Application implements GameListener {

    private Model model;
    private BorderPane root;
    private Board board;
    @Override
    public void taskCompleted(int left, Cell cell) {
        boolean mistake;
        mistake = (left == -1);
        System.out.println(left);
        board.getCellButton(cell).paint(mistake);
    }

    @Override
    public void taskCreated(List<Cell> taskCopy) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(750),
                        ev -> {
                            board.getCellButton(taskCopy.get(taskCopy.size()-1)).paint(false);
                            taskCopy.remove(taskCopy.size()-1);
                        }));
        timeline.setCycleCount(taskCopy.size());
        timeline.play();

    }

    public void buildGUI(Stage mainStage){

        root = new BorderPane();
        BoardListener listener = new BoardListener(model);

        board = new Board(model, listener);
        root.setCenter(board);
        Scene scene = new Scene(root, 500, 500);
        mainStage.setScene(scene);
        mainStage.setTitle("Testing");
        //mainStage.setResizable(false);
        mainStage.setAlwaysOnTop(true);
    }


    public void start(Stage mainStage) throws Exception{
        DialogWindow dialogWindow = new DialogWindow();
        Optional<ButtonType> result = dialogWindow.showAndWait();
        System.out.println(dialogWindow.getBoardSize());
        if(result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE)
        {
            model = new Model();
            model.addListener(this);
            buildGUI(mainStage);
            mainStage.show();
            model.createTask(3);
        }

    }
    public static void main(String[] args){
        launch(args);
    }
}