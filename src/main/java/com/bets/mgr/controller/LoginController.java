package com.bets.mgr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public String login(
            @RequestParam(name = "logout", required = false) String logout,
            @RequestParam(name = "error", required = false) String error,
            Model model, Principal principal, RedirectAttributes flash) {

        if (principal != null) {
            flash.addAttribute("info", "Logged");
            return "redirect:/matches";
        }

        if (error != null) {
            model.addAttribute("error", "There was an error!");
        }

        if (logout != null) {
            model.addAttribute("success", "You have closed session!");
        }

        return "login";
    }
}
