package com.luxoft.javafx.service;

import com.luxoft.javafx.model.domain.ElectronicCalculation;
import com.luxoft.javafx.model.repository.ElectronicCalculationRepository;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.luxoft.javafx.model.domain.CalculationMode.PARALLEL;
import static com.luxoft.javafx.model.domain.ElectronicComponentType.RESISTOR;

@Service
public class ResistanceListResultsServiceImpl implements ResistanceListResultsService {

    @Autowired
    private ElectronicCalculationRepository repository;

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
                        data.append(r.getValue());
                        data.append(ending);
                        data.append(", ");
                    });
                    data.append("turns to ");
                    data.append(ec.getResult().getValue());
                    data.append(ending);
                    Label l = new Label(data.toString());
                    l.getStyleClass().add("resultLabel");
                    historyCalculation.getChildren().add(l);
                });
    }
}
