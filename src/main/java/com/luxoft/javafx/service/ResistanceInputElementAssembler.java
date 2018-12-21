package com.luxoft.javafx.service;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ResistanceInputElementAssembler {
    public static Label newLabel(int sequence) {

        Label label = new Label();
        label.setText("R" + (sequence + 1) + ": ");
        label.setAlignment(Pos.CENTER);

        return label;
    }


    public static TextField newTextField() {

        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER_LEFT);
        textField.getStyleClass().add("resistanceInput");

        return textField;
    }

    public static HBox newHBox() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getStyleClass().add("hBox");

        return hBox;
    }
}
