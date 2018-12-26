package com.luxoft.javafx.model.repository;

import com.luxoft.javafx.model.domain.ElectronicComponent;
import com.luxoft.javafx.model.domain.ElectronicComponentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectronicComponentRepository extends JpaRepository<ElectronicComponent, Long> {
    Optional<ElectronicComponent> findByValueAndType(Double value, ElectronicComponentType type);
}
