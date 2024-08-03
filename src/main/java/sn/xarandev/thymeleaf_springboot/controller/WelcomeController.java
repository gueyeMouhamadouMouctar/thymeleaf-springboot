package sn.xarandev.thymeleaf_springboot.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest req, HttpServletResponse resp, Model map) {
        String email = req.getRemoteUser();
		/*UserDto user = userService.getByEmail(email);
		map.addAttribute("user", user);*/
        return "welcome";
    }
}
