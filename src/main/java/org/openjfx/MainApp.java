package org.openjfx;

import Controller.BoardListener;
import Model.GameListener;
import Model.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class MainApp extends Application implements GameListener {

    private Model model;
    @Override
    public void taskCompleted(int left, Cell cell) {

    }

    @Override
    public void taskCreated() {

    }

    public void buildGUI(Stage mainStage){

        BorderPane root = new BorderPane();
        BoardListener listener = new BoardListener(model);

        GridPane grid = new GridPane();
        /*
        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setTopAnchor(grid, 10d);
        AnchorPane.setBottomAnchor(grid, 10d);
        AnchorPane.setLeftAnchor(grid, 10d);
        AnchorPane.setRightAnchor(grid, 10d);
        anchorPane.getChildren().add(grid);
*/
        root.setCenter(grid);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setAlignment(Pos.CENTER);
        for(int i = 0; i < model.numOfRows; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100d/model.numOfRows);
            grid.getRowConstraints().add(row);
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100d/model.numOfColumns);
            grid.getColumnConstraints().add(col);
            for(int j = 0; j < model.numOfColumns; j++) {
                CellButton btn = new CellButton(model.getCell(i, j), model, listener);
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                grid.add(btn, j, i);
            }
        }

        Scene scene = new Scene(root, 500, 500);
        mainStage.setScene(scene);
        mainStage.setTitle("Testing");
        //mainStage.setResizable(false);

    }
    public void start(Stage mainStage) throws Exception{
        //DialogWindow dialogWindow = new DialogWindow();
        model = new Model();
        model.addListener(this);
        buildGUI(mainStage);
        mainStage.show();

    }
    public static void main(String[] args){
        launch(args);
    }
}