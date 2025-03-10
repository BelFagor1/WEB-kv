package com.example.kv.repo;

import com.example.kv.model.DroneToFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneToFileRepository extends CrudRepository<DroneToFile,Long> {
}
