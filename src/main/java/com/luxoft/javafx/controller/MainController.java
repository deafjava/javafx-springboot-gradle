package com.luxoft.javafx.controller;

import com.luxoft.javafx.assembler.ResistanceInputElement;
import com.luxoft.javafx.service.ResistanceParallelCalculationService;
import com.luxoft.javafx.service.ResistanceListResultsService;
import com.luxoft.javafx.service.exception.MinimumResistancesInputReachedException;
import com.luxoft.javafx.utils.ReadAndCalculateUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Set;

import static com.luxoft.javafx.assembler.ResistanceInputElement.RESIS_SELECTOR;

@Controller
public class MainController {

    @FXML
    public VBox refBox;

    @FXML
    public Label resultResistance;

    @FXML
    public Button addR;

    @FXML
    public Button removeR;

    @FXML
    public Button saveR;

    @FXML
    public VBox historyCalculation;

    @Autowired
    private ResistanceParallelCalculationService resistanceParallelCalculationService;

    @Autowired
    private ResistanceListResultsService resistanceListResultsService;

    @FXML
    public void initialize() {

        Set<Node> rs = refBox.lookupAll(RESIS_SELECTOR);

        rs.stream()
                .filter(node -> node instanceof TextField)
                .forEach(node -> node.setOnKeyReleased(event -> ReadAndCalculateUtils.asParallel(refBox, resultResistance)));

        addR.setOnMouseClicked(e -> {

            ResistanceInputElement.create(refBox, resultResistance);

            ReadAndCalculateUtils.asParallel(refBox, resultResistance);
        });

        removeR.setOnMouseClicked(event -> {

            try {
                ResistanceInputElement.remove(refBox);

                ReadAndCalculateUtils.asParallel(refBox, resultResistance);

            } catch (MinimumResistancesInputReachedException e) {
                Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);
                dialogoInfo.setTitle("Ooops!");
                dialogoInfo.setHeaderText(e.getMessage());
                dialogoInfo.showAndWait();
            }
        });

        saveR.setOnMouseClicked(event -> {
            resistanceParallelCalculationService.register(refBox, resultResistance);
            resistanceListResultsService.update(historyCalculation);
        });
    }
}
