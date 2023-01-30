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
    private TopBar topBar;
    private Stage mainStage;
    @Override
    public void taskCompleted(int left, Cell cell) {
        System.out.println("Left " + left);
        board.getCellButton(cell).paint(false);
    }

    @Override
    public void taskFailed(Cell cell){
        System.out.println("Failed");
        board.getCellButton(cell).paint(true);
    }

    @Override
    public void taskError(int errors, Cell cell){
        System.out.println("Errors " + errors);
        board.getCellButton(cell).paint(true);
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

    private void buildGUI(Stage mainStage){

        root = new BorderPane();
        BoardListener listener = new BoardListener(model);

        board = new Board(model, listener);
        root.setCenter(board);
        topBar = new TopBar(this, mainStage);
        root.setTop(topBar);

        Scene scene = new Scene(root, 500, 500);
        mainStage.setScene(scene);
        mainStage.setTitle("Testing");
        //mainStage.setResizable(false);

        mainStage.show();
    }

    public void newGame(){
        DialogWindow dialogWindow = new DialogWindow();
        Optional<ButtonType> result = dialogWindow.showAndWait();
        if(result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE)
        {
            model = new Model(dialogWindow.getBoardSize());
            model.addListener(this);
            buildGUI(mainStage);
            model.createTask(dialogWindow.getDifficulty(), dialogWindow.getErrorsNumber());
            dialogWindow.close();
        }

    }

    public void restartGame(){
        model.restartTask();
    }
    public void start(Stage mainStage) throws Exception{
        this.mainStage = mainStage;
        newGame();
    }



    public static void main(String[] args){
        launch(args);
    }
}