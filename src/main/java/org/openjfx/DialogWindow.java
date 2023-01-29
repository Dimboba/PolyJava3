package org.openjfx;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DialogWindow extends Dialog<ButtonType> {

    private final ToggleGroup errors = new ToggleGroup();
    private final ComboBox<String> comboBox;
    public int getErrorsNumber(){
        RadioButton btn = (RadioButton) errors.getSelectedToggle();
        if(btn.getText().equals('\u221E' + "")) return -1;
        return Integer.parseInt(btn.getText());
    }

    public int getBoardSize(){
        return Integer.parseInt(comboBox.getValue().substring(0, 1));
    }

    public DialogWindow(){
        DialogPane root = this.getDialogPane();
        root.getButtonTypes().add(new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE));
        root.getButtonTypes().add(new ButtonType("Start game", ButtonBar.ButtonData.OK_DONE));

        HBox hbox = new HBox(30);
        root.setContent(hbox);
        VBox vbox1 = new VBox(10);
        VBox vbox2 = new VBox(10);
        hbox.getChildren().addAll(vbox1, vbox2);

        Label errorLabel = new Label("Errors Allowed:");
        vbox1.getChildren().add(errorLabel);
        RadioButton zero = new RadioButton("0");
        RadioButton three = new RadioButton("3");
        RadioButton five = new RadioButton("5");
        RadioButton inf = new RadioButton('\u221E' + "");
        zero.isSelected();
        errors.getToggles().addAll(zero, three, five, inf);
        vbox1.getChildren().addAll(zero, three, five, inf);

        Label boardSizeLabel = new Label("Board's size:");
        char times = '\u00D7';
        String t = " " +times + " ";
        ObservableList<String> comboList = FXCollections.observableArrayList(
                3 + t + 3, 4 + t + 4, 5 + t + 5, 6 + t + 6);
        comboBox = new ComboBox<>(comboList);
        comboBox.setValue(4 + t + 4);
        vbox2.getChildren().addAll(boardSizeLabel, comboBox);
    }
}
