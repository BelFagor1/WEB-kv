package com.example.kv.controller;

import com.example.kv.model.*;
import com.example.kv.model.enumeration.UserType;
import com.example.kv.repo.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class AdminController {
    @Autowired
    private SuperPasswordRepository superPasswordRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private DroneToFileRepository droneToFileRepository;
    @Autowired
    private FileRepository fileRepository;

    @GetMapping("/admin/menu")
    public String admin(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (currentUser.getUserType().equals(UserType.ADMIN)) {
            return "admin";
        } else {
            return "newAdmin";
        }
    }

    @GetMapping("/token/generator")
    public String verification(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (currentUser.getUserType().equals(UserType.ADMIN)) {
            return "verification";
        } else {
            return "newAdmin";
        }
    }

    @PostMapping("/admin/verification")
    public String generator(@RequestParam String password, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (currentUser.getUserType().equals(UserType.ADMIN)) {
            SuperPassword superPassword = superPasswordRepository.findById(1L).get();
            if (superPassword.getPasswordHash().equals(String.valueOf(password.hashCode()))) {
                return "generator";
            } else return "admin";
        } else return "newAdmin";
    }

    @PostMapping("/token/confirm")
    public String confirm(@RequestParam String token, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (currentUser.getUserType().equals(UserType.ADMIN)) {
            tokenRepository.save(new Token(token));
            return "admin";
        } else return "newAdmin";
    }

    @PostMapping("/admin/new/confirm")
    public String confirmNewAdmin(@RequestParam String token, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        Iterable<Token> tokens = tokenRepository.findAll();
        for (Token tok : tokens) {
            if (tok.getToken().equals(token)) {
                User user = userRepository.findById(currentUser.getId()).get();
                user.setUserType(UserType.ADMIN);
                userRepository.save(user);
                tokenRepository.delete(tok);
                currentUser.setUserType(UserType.ADMIN);
                return "admin";
            }
        }
        return "newAdmin";
    }

    @GetMapping("/upload/list")
    public String uploadList(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (currentUser.getUserType().equals(UserType.ADMIN)) {
            Iterable<Drone> droneList = droneRepository.findAll();
            model.addAttribute("droneList", droneList);
            return "uploadList";
        } else {
            return "newAdmin";
        }
    }

    @PostMapping("/api/files/delete/{id}")
    public String deleteFile(@PathVariable(value = "id") long id, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (currentUser.getUserType().equals(UserType.ADMIN)) {
            Iterable<DroneToFile> droneToFileList = droneToFileRepository.findAll();
            for (DroneToFile droneToFile : droneToFileList) {
                if (droneToFile.getFileID() == id) {
                    droneToFileRepository.delete(droneToFile);
                    fileRepository.deleteById(id);
                    return "admin";
                }
            }
        }
        return "newAdmin";
    }

    @PostMapping("/upload/selected/{id}")
    public String res(@PathVariable(value = "id") long id, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (currentUser.getUserType().equals(UserType.ADMIN)) {
            Drone drone = droneRepository.findById(id).get();
            model.addAttribute("drone", drone);
            ArrayList<DroneToFile> list = new ArrayList<>();
            Iterable<DroneToFile> links = droneToFileRepository.findAll();
            for (DroneToFile link : links) {
                if (link.getDroneID() == drone.getId()) {
                    list.add(link);
                }
            }
            ArrayList<FileEntity> files = new ArrayList<>();
            for (DroneToFile link : list) {
                files.add(fileRepository.findById(link.getFileID()).get());
            }
            model.addAttribute("files", files);
            return "uploadDrone";
        } else {
            return "newAdmin";
        }
    }
}
