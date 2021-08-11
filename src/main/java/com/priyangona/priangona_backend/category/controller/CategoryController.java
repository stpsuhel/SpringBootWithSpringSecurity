package com.priyangona.priangona_backend.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.priyangona.priangona_backend.util.Constant.USER_PAGE;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("/page")
    public String productPage(Model model){

        model.addAttribute("title", "Category");
        model.addAttribute("folder", "category");
        model.addAttribute("fragment", "category");
        model.addAttribute("script", "category");
        model.addAttribute("activeIdForActivePage", "activeCategoryPage");

        return USER_PAGE;
    }

}
