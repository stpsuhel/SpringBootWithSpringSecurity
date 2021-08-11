package com.priyangona.priangona_backend.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.priyangona.priangona_backend.util.Constant.USER_PAGE;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHomePage(Model model) {

        model.addAttribute("title", "Home");
        model.addAttribute("folder", "home");
        model.addAttribute("fragment", "home");
        model.addAttribute("script", "home");

        return USER_PAGE;
    }
}
