package com.example.kv.controller;

import com.example.kv.model.FileEntity;
import com.example.kv.repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
public class FileDownloadController {

    @Autowired
    private FileRepository fileRepository;

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
        // Ищем файл в базе данных
        Optional<FileEntity> fileEntityOptional = fileRepository.findById(id);

        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();

            // Логируем размер файла
            System.out.println("Размер файла в базе: " + fileEntity.getData().length + " байт");

            // Кодируем имя файла для корректной передачи в заголовке
            String encodedFileName;
            try {
                encodedFileName = URLEncoder.encode(fileEntity.getFileName(), StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                encodedFileName = "file"; // Если кодирование не удалось, используем простое имя
            }

            // Возвращаем файл клиенту
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
                    .body(new ByteArrayResource(fileEntity.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}