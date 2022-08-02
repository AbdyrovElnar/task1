package com.example.task1.controller;

import com.example.task1.enity.User;
import com.example.task1.service.FileService;
import com.example.task1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final UserService userService;


    @GetMapping
    public String getMainPage(){
        return "/index";
    }
    @PostMapping
    public String handleData(@RequestParam("fileSelect") MultipartFile fileSelect){
        try {
            fileService.saveFileInfo(fileSelect);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/index";
    }

    @GetMapping("/register")
    public String pageRegisterCustomer(Model model) {
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new User());
        }

        return "/register";
    }

    @PostMapping("/register")
    public String registerPage(@RequestParam("email") String email,
                               @RequestParam("password") String password) {

        userService.register(email,password);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

}
