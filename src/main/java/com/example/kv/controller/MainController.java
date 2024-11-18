package com.example.kv.controller;

import com.example.kv.model.Drone;
import com.example.kv.repo.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private DroneRepository droneRepository;
    @GetMapping("/")
    public String mainPage(Model model){
        Iterable<Drone> droneList = droneRepository.findAll();
        model.addAttribute("droneList", droneList);
        return "index";
    }
}
