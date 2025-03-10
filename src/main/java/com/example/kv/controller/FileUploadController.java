package com.example.kv.controller;

import com.example.kv.model.DroneToFile;
import com.example.kv.model.FileEntity;
import com.example.kv.repo.DroneToFileRepository;
import com.example.kv.repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private DroneToFileRepository droneToFileRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam long droneID) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Файл пустой!");
            }

            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setData(file.getBytes());

            fileRepository.save(fileEntity);
            DroneToFile link = new DroneToFile(droneID, fileEntity.getId());
            droneToFileRepository.save(link);

            return ResponseEntity.ok("Файл успешно загружен: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Ошибка при загрузке файла!");
        }
    }
}