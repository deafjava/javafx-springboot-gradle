package com.luxoft.javafx.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "electronic_calculation")
public class ElectronicCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "electronic_calculation_components",
            joinColumns = @JoinColumn(name = "id_calculation", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_component", referencedColumnName = "id")
    )
    private List<ElectronicComponent> components;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "result")
    private ElectronicComponent result;

    @Enumerated(EnumType.STRING)
    private CalculationMode mode;

    @Enumerated(EnumType.STRING)
    private ElectronicComponentType type;
}
