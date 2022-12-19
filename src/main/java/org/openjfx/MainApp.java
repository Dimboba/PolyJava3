package org.openjfx;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.*;

import java.util.Optional;

public class MainApp extends Application {


    @Override public void start(Stage mainStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        mainStage.setScene(scene);
        mainStage.setTitle("Testing");
        DialogWindow dialogWindow = new DialogWindow();
        mainStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
