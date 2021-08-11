package com.priyangona.priangona_backend.cart.controller;

import com.priyangona.priangona_backend.cart.service.CartService;
import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.priyangona.priangona_backend.util.Constant.USER_PAGE;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/page")
    public String categoryPage(Model model){

        model.addAttribute("title", "Cart");
        model.addAttribute("folder", "cart");
        model.addAttribute("fragment", "cart");
        model.addAttribute("script", "cart");

        return USER_PAGE;
    }
}
