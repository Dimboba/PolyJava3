package org.openjfx;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

public class DialogWindow extends Dialog<ButtonType> {
    public DialogWindow(){
        DialogPane root = this.getDialogPane();
        root.getButtonTypes().add(new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE));
        root.getButtonTypes().add(new ButtonType("Start game", ButtonBar.ButtonData.OK_DONE));

    }
}
