package com.luxoft.javafx.service;

import com.luxoft.javafx.model.domain.ElectronicCalculation;
import com.luxoft.javafx.model.repository.ElectronicCalculationRepository;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.luxoft.javafx.model.domain.CalculationMode.PARALLEL;
import static com.luxoft.javafx.model.domain.ElectronicComponentType.RESISTOR;

@Service
public class ResistanceListResultsServiceImpl implements ResistanceListResultsService {

    @Autowired
    private ElectronicCalculationRepository repository;

    @Autowired
    private ResistanceListResultsService resistanceListResultsService;

    @Autowired
    private ComponentCalculationService componentCalculationService;

    @Override
    public void update(VBox historyCalculation) {

        List<ElectronicCalculation> list = repository.findAll();

        historyCalculation.getChildren().clear();

        list.stream()
                .filter(ec -> ec.getType().equals(RESISTOR) && ec.getMode().equals(PARALLEL))
                .forEach(ec -> {
                    StringBuilder data = new StringBuilder();
                    String ending = ec.getType().getUnit().getEnding();
                    ec.getComponents().forEach(r -> {
                        data.append(String.format(Locale.ENGLISH, "%.2f", r.getValue()));
                        data.append(ending);
                        data.append(", ");
                    });
                    data.append("turns to ");
                    data.append(String.format(Locale.ENGLISH, "%.2f", ec.getResult().getValue()));
                    data.append(ending);
                    Label l = new Label(data.toString());
                    l.setId(String.valueOf(ec.getId()));

                    Tooltip tp = new Tooltip("Double click to remove this...");
                    Tooltip.install(l, tp);

                    l.setOnMouseClicked(event -> {
                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                            if (event.getClickCount() == 2) {

                                Alert dialogoInfo = new Alert(Alert.AlertType.CONFIRMATION);
                                dialogoInfo.setTitle("Would you like to remove this registration?");
                                dialogoInfo.setHeaderText(((Label) event.getSource()).getText());
                                Optional<ButtonType> pressedButton = dialogoInfo.showAndWait();
                                pressedButton.ifPresent(b -> {
                                    if (b.equals(ButtonType.OK)) {
                                        componentCalculationService.delete((Node) event.getSource());
                                        resistanceListResultsService.update(historyCalculation);
                                    }
                                });
                            }
                        }
                    });
                    l.getStyleClass().add("resultLabel");
                    historyCalculation.getChildren().add(l);
                });
    }
}
