package sn.xarandev.thymeleaf_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sn.xarandev.thymeleaf_springboot.dto.UserDto;
import sn.xarandev.thymeleaf_springboot.service.IUserService;

@Controller
public class AdminController {

    private IUserService userService;

    public AdminController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {

        model.addAttribute("usersList", userService.getAll());
        model.addAttribute("user", new UserDto());
        return "users/list";
    }

    @PostMapping("/admin")
    public String admin(@ModelAttribute("user") UserDto user) {

        userService.save(user);
        return "redirect:/admin";
    }
}