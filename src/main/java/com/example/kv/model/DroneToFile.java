package com.example.kv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDroneID() {
        return droneID;
    }

    public void setDroneID(long droneID) {
        this.droneID = droneID;
    }

    public long getFileID() {
        return fileID;
    }

    public void setFileID(long fileID) {
        this.fileID = fileID;
    }
}
