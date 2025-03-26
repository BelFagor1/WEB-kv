package com.example.kv.model;

import jakarta.persistence.*;


@Entity
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
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    @Column(length = 10000)
    private String description;
    private Boolean status;

    public Drone(String name, double maxFlightDistance, double combatRadius, double maxSpeed,
                 double maxFlightAltitude, double maxFlightDuration, double payloadWeight,
                 double warheadWeight, double wingspan, double length, String engineType,
                 byte[] image, String description) {
        this.name = name;
        this.maxFlightDistance = maxFlightDistance;
        this.combatRadius = combatRadius;
        this.maxSpeed = maxSpeed;
        this.maxFlightAltitude = maxFlightAltitude;
        this.maxFlightDuration = maxFlightDuration;
        this.payloadWeight = payloadWeight;
        this.warheadWeight = warheadWeight;
        this.wingspan = wingspan;
        this.length = length;
        this.engineType = engineType;
        this.image = image;
        this.description = description;
        this.status = Boolean.FALSE;
    }

    public Drone() {

    }

    public Boolean getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxFlightDistance() {
        return maxFlightDistance;
    }

    public void setMaxFlightDistance(double maxFlightDistance) {
        this.maxFlightDistance = maxFlightDistance;
    }

    public double getCombatRadius() {
        return combatRadius;
    }

    public void setCombatRadius(double combatRadius) {
        this.combatRadius = combatRadius;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getMaxFlightAltitude() {
        return maxFlightAltitude;
    }

    public void setMaxFlightAltitude(double maxFlightAltitude) {
        this.maxFlightAltitude = maxFlightAltitude;
    }

    public double getMaxFlightDuration() {
        return maxFlightDuration;
    }

    public void setMaxFlightDuration(double maxFlightDuration) {
        this.maxFlightDuration = maxFlightDuration;
    }

    public double getPayloadWeight() {
        return payloadWeight;
    }

    public void setPayloadWeight(double payloadWeight) {
        this.payloadWeight = payloadWeight;
    }

    public double getWarheadWeight() {
        return warheadWeight;
    }

    public void setWarheadWeight(double warheadWeight) {
        this.warheadWeight = warheadWeight;
    }

    public double getWingspan() {
        return wingspan;
    }

    public void setWingspan(double wingspan) {
        this.wingspan = wingspan;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
