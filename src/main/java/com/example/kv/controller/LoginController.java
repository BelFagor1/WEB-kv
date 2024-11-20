package com.example.kv.controller;

import com.example.kv.data.EmailRequest;
import com.example.kv.data.ErrorMessage;
import com.example.kv.data.PasswordHashingUtil;
import com.example.kv.model.User;
import com.example.kv.model.enumeration.UserType;
import com.example.kv.repo.UserRepository;
import com.example.kv.service.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
@SessionAttributes("error")
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordHashingUtil passwordHashingUtil;

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getEmail().equals(email) && passwordHashingUtil.verifyPassword(password, user.getSalt(), user.getPasswordHash())) {
                session.setAttribute("user", user);
                return "redirect:/";
            }
        }
        ErrorMessage errorMessage = new ErrorMessage("We do not find any user with that email or your password is incorrect");
        model.addAttribute("error", errorMessage);
        return "redirect:/registrationError";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    private String generatedCode;
    @PostMapping("/api/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest email){
        generatedCode = emailService.sendConfirmationEmail(email.getEmail());
        return ResponseEntity.ok().body(email.getEmail());
    }
    @PostMapping("/registration")
    public String registration(Model model, @RequestParam String email, @RequestParam String password, @RequestParam String repeatPassword, @RequestParam String code, HttpSession session ) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (password.trim().equals(repeatPassword.trim())) {
            if(generatedCode.equals(code)) {
                String salt = passwordHashingUtil.generateSalt();
                    String hashedPassword = passwordHashingUtil.hashPassword(password, salt);

                User user = new User(email, hashedPassword, salt);
                userRepository.save(user);
                session.setAttribute("user", user);
                return "redirect:/";
            }
            else {
                ErrorMessage errorMessage = new ErrorMessage("Verification code is incorrect");
                model.addAttribute("error", errorMessage);
                return "redirect:/registrationError";
            }
        }
        else {
            ErrorMessage errorMessage = new ErrorMessage("Passwords do not match");
            model.addAttribute("error", errorMessage);
            return "redirect:/registrationError";
        }
    }
    @GetMapping("/registrationError")
    public String registrationError(Model model) {
        System.out.println(model.getAttribute("error"));
        return "registrationError";
    }
}
