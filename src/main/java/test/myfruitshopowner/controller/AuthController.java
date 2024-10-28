package test.myfruitshopowner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final ReactiveAuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }
}