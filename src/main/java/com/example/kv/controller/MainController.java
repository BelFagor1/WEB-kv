package com.example.kv.controller;

import com.example.kv.model.Drone;
import com.example.kv.model.DroneToFile;
import com.example.kv.model.FileEntity;
import com.example.kv.model.User;
import com.example.kv.repo.DroneRepository;
import com.example.kv.repo.DroneToFileRepository;
import com.example.kv.repo.FileRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String res(@PathVariable(value = "id") long id, Model model, Model model2, HttpSession session){
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
        System.out.println(files.size());
        model2.addAttribute("files", files);
        return "redirect:/selected";
    }
    @GetMapping("/selected")
    public String drones(Model model, Model model2, HttpSession session){
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            return "redirect:/login";
        }
        return "detailDrone";
    }
//    @GetMapping("/notReady")
//    public String notReady(Model model,HttpSession session){
//        User currentUser = (User) session.getAttribute("user");
//
//        if (currentUser == null) {
//            return "redirect:/login";
//        }
//        return "notReady";
//    }
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
}
