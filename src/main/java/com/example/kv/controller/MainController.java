package com.example.kv.controller;

import com.example.kv.model.*;
import com.example.kv.repo.DroneRepository;
import com.example.kv.repo.DroneToFileRepository;
import com.example.kv.repo.FileRepository;
import com.example.kv.repo.SuperPasswordRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("drone")
public class MainController {
    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private DroneToFileRepository droneToFileRepository;
    @Autowired
    private FileRepository fileRepository;
    @GetMapping("/")
    public String mainPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }
        Iterable<Drone> droneList = droneRepository.findAll();
        model.addAttribute("droneList", droneList);
        return "index";
    }
    @PostMapping("/selected/{id}")
    public String res(@PathVariable(value = "id") long id, Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        Drone drone = droneRepository.findById(id).get();
        model.addAttribute("drone", drone);
        ArrayList<DroneToFile> list = new ArrayList<>();
        Iterable<DroneToFile> links = droneToFileRepository.findAll();
        for (DroneToFile link : links) {
            if (link.getDroneID() == drone.getId()){
                list.add(link);
            }
        }
        ArrayList<FileEntity> files = new ArrayList<>();
        for (DroneToFile link : list){
            files.add(fileRepository.findById(link.getFileID()).get());
        }
        model.addAttribute("files", files);
        return "detailDrone";
    }
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
    List<Drone> droneList;
    @PostMapping("/search")
    public String search(Model model, HttpSession session, @RequestParam String str){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        droneList = droneRepository.findByNameContaining(str);
        return "redirect:/search";
    }
    @GetMapping("/search")
    public String search(Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("droneList", droneList);
        return "search";
    }
    @GetMapping("/entity/{id}/image")
    public ResponseEntity<byte[]> getEntityImage(@PathVariable Long id) {
        byte[] image = droneRepository.findById(id).get().getImage();
        if (image == null) {
            return ResponseEntity.notFound().build();
        }
        String mimeType = null;
        try {
            mimeType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (mimeType == null) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // На случай, если не удалось определить
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mimeType)); // Или IMAGE_PNG, если у вас PNG
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

}
