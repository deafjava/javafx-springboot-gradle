package com.luxoft.javafx.service;

import com.luxoft.javafx.model.domain.ElectronicComponent;
import com.luxoft.javafx.model.domain.ElectronicComponentFactory;
import com.luxoft.javafx.model.domain.ElectronicComponentType;
import com.luxoft.javafx.model.repository.ElectronicComponentRepository;
import com.luxoft.javafx.service.exception.NotCompatibleViewException;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResistorComponentServiceImpl implements ResistorComponentService {

    @Autowired
    private ElectronicComponentRepository repository;

    @Override
    public ElectronicComponent save(Node labelValue) {

        String datum;

        // Although of same method name - getText - they're different by encapsulated implementation
        if (labelValue instanceof TextField) {
            datum = ((TextField) labelValue).getText();
        } else if (labelValue instanceof Label) {
            datum = ((Label) labelValue).getText();
        } else {
            throw new NotCompatibleViewException();
        }

        double v = Double.parseDouble(datum);

        Optional<ElectronicComponent> electronicComponentOpt = repository.findByValueAndType(v, ElectronicComponentType.RESISTOR);

        return electronicComponentOpt.orElseGet(() -> repository.save(ElectronicComponentFactory.resistor(v)));
    }
}
