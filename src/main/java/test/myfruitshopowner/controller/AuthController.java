package test.myfruitshopowner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }
}