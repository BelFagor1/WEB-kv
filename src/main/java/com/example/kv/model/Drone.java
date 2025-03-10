package com.example.kv.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    // Максимальна дальність польоту, км
    private double maxFlightDistance;
    // Бойовий радіус, км
    private double combatRadius;
    // Максимальна швидкість, км/год
    private double maxSpeed;
    // Максимальна висота польоту, км
    private double maxFlightAltitude;
    // Тривалість польоту (макс), год
    private double maxFlightDuration;
    // Маса корисного навантаження, кг
    private double payloadWeight;
    // Маса бойової частини, кг
    private double warheadWeight;
    // Розмах крил, м
    private double wingspan;
    // Довжина, м
    private double length;
    // Силова установка (тип двигуна)
    private String engineType;
    private String pathToImg;
    @Column(length = 10000)
    private String description;

    public Drone(String name, double maxFlightDistance, double combatRadius, double maxSpeed, double maxFlightAltitude,
                 double maxFlightDuration, double warheadWeight, double payloadWeight, double wingspan,
                 double length, String engineType) {
        this.maxFlightDistance = maxFlightDistance;
        this.combatRadius = combatRadius;
        this.maxSpeed = maxSpeed;
        this.maxFlightAltitude = maxFlightAltitude;
        this.maxFlightDuration = maxFlightDuration;
        this.warheadWeight = warheadWeight;
        this.payloadWeight = payloadWeight;
        this.wingspan = wingspan;
        this.length = length;
        this.engineType = engineType;
        this.name = name;
    }

    public Drone() {

    }


}
