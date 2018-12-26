package com.luxoft.javafx.model.repository;

import com.luxoft.javafx.model.domain.ElectronicCalculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectronicCalculationRepository extends JpaRepository<ElectronicCalculation, Long> {
}
