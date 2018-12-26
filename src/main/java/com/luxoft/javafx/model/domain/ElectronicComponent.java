package com.luxoft.javafx.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Table(name = "electronic_component", uniqueConstraints = @UniqueConstraint(columnNames = {"value", "type"}))
@Entity
@EqualsAndHashCode(of = {"id"})
public class ElectronicComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    @Enumerated(EnumType.STRING)
    private ElectronicComponentType type;
}
