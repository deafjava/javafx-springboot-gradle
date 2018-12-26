package com.luxoft.javafx.assembler;

import com.luxoft.javafx.service.exception.MinimumResistancesInputReachedException;
import com.luxoft.javafx.utils.ReadAndCalculateUtils;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Set;

public class ResistanceInputElement {

    public static final String RESIS_SELECTOR = ".resistanceInput";

    public static void remove(VBox refBox) throws MinimumResistancesInputReachedException {
        Set<Node> currentRs = refBox.lookupAll(RESIS_SELECTOR);
        if (currentRs.size() > 2) {
            refBox.getChildren().remove(currentRs.size());
        } else {
            throw new MinimumResistancesInputReachedException();
        }

    }

    public static void create(VBox refBox, Label resultResistance) {

        Set<Node> currentRs = refBox.lookupAll(RESIS_SELECTOR);

        HBox hBox = ResistanceInputElementAssembler.newHBox();

        hBox.getChildren().add(ResistanceInputElementAssembler.newLabel(currentRs.size()));

        TextField t = ResistanceInputElementAssembler.newTextField();

        t.setOnKeyReleased(event -> ReadAndCalculateUtils.asParallel(refBox, resultResistance));

        hBox.getChildren().add(t);

        refBox.getChildren().add(currentRs.size() + 1, hBox);

    }



}
