package com.example.kv.controller;

import com.example.kv.model.Drone;
import com.example.kv.repo.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("drone")
public class MainController {
    @Autowired
    private DroneRepository droneRepository;
    @GetMapping("/")
    public String mainPage(Model model){
        Iterable<Drone> droneList = droneRepository.findAll();
        model.addAttribute("droneList", droneList);
        return "index";
    }
    @PostMapping("/selected/{id}")
    public String res(@PathVariable(value = "id") long id, Model model){
        Drone drone = droneRepository.findById(id).get();
        model.addAttribute("drone", drone);
        return "redirect:/selected";
    }
    @GetMapping("/selected")
    public String drones(Model model){
        return "detailDrone";
    }
    @GetMapping("/notReady")
    public String notReady(Model model){
        return "notReady";
    }
}
