package org.openjfx;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TopBar extends VBox {

    public TopBar(MainApp mainApp, Stage mainStage) {
        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Game");
        MenuItem restartGame = new MenuItem("Restart");
        restartGame.setOnAction(ev ->{
            mainApp.restartGame();
        });
        MenuItem newGame = new MenuItem("NewGame");
        newGame.setOnAction(ev -> {
            mainStage.hide();
            mainApp.newGame();
        });
        MenuItem exitGame = new MenuItem("Exit");
        exitGame.setOnAction(ev -> {
            mainStage.close();
        });
        //MenuItem sounds = new MenuItem("Sounds On");
        gameMenu.getItems().addAll(restartGame, newGame, exitGame);
        menuBar.getMenus().addAll(gameMenu);
        this.getChildren().add(menuBar);
    }
}
