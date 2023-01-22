package org.openjfx;

import Model.GameListener;
import Model.Model;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class MainApp extends Application implements GameListener {

    private Model model;
    @Override
    public void taskCompleted(int left, int lastRow, int lastCellNum) {

    }

    private void buildGUI(Stage mainStage){
        BorderPane borderPane = new BorderPane();
        GridPane grid = new GridPane();
        borderPane.setCenter(grid);

        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setAlignment(Pos.CENTER);
        //grid.add(new Text("Test"), 2, 2);
        for(int i = 0; i < model.numOfRows; i++) {
            for(int j = 0; j < model.cellsInRow; j++) {
                Button btn = new Button();
        /* //someTestsOnActions
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //btn.setColor(Color.FIREBRICK);
                btn.setText("Sign in button pressed");
            }
        });
        */
                grid.add(btn, i, j);
            }
        }
        HBox hBox = new HBox();
        hBox.setMinHeight(200);
        borderPane.setTop(hBox);
        Group root = new Group(borderPane);
        Scene scene = new Scene(root, 500, 500);
        mainStage.setScene(scene);
        mainStage.setTitle("Testing");

    }
    public void start(Stage mainStage) throws Exception{
        //DialogWindow dialogWindow = new DialogWindow();
        model = new Model();
        buildGUI(mainStage);
        mainStage.show();

    }
    public static void main(String[] args){
        launch(args);
    }
}
