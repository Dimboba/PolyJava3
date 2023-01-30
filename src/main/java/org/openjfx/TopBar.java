package org.openjfx;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TopBar extends HBox {

    public TopBar(MainApp mainApp, Stage mainStage) {
        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Game");
        MenuItem restartGame = new MenuItem("Restart");
        restartGame.setOnAction(ev ->{
            mainApp.restartGame();
        });
        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(ev -> {
            mainStage.hide();
            mainApp.newGame();
        });
        MenuItem exitGame = new MenuItem("Exit");
        exitGame.setOnAction(ev -> {
            mainStage.close();
        });
        gameMenu.getItems().addAll(restartGame, newGame, exitGame);
        menuBar.getMenus().addAll(gameMenu);
        ToggleButton soundButton = new ToggleButton("Sound On");
        soundButton.setSelected(true);
        soundButton.setOnAction(ev -> {
            if(soundButton.isSelected()) {
                soundButton.setText("Sounds On");
                //врубаем звуки
            }
            else{
                soundButton.setText("Sounds Off");
                //вырубаем звук
            }
        });
        this.getChildren().addAll(menuBar, soundButton);
    }
}
