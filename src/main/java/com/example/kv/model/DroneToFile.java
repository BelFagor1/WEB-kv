package com.example.kv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DroneToFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long droneID;
    private long fileID;
    public DroneToFile(long droneID, long fileID) {
        this.droneID = droneID;
        this.fileID = fileID;
    }

    public DroneToFile() {
    }
}
