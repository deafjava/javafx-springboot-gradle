package com.luxoft.javafx.service;

import com.luxoft.javafx.model.repository.ElectronicCalculationRepository;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponentCalculationServiceImpl implements ComponentCalculationService {

    @Autowired
    private ElectronicCalculationRepository repository;

    @Override
    public void delete(Node node) {
        Long id = Long.valueOf(node.getId());
        repository.deleteById(id);
    }
}
