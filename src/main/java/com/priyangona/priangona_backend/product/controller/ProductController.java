package com.priyangona.priangona_backend.product.controller;

import com.priyangona.priangona_backend.product.model.Product;
import com.priyangona.priangona_backend.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.priyangona.priangona_backend.util.Constant.USER_PAGE;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/page")
    public String productPage(Model model){

        model.addAttribute("title", "Product");
        model.addAttribute("folder", "product");
        model.addAttribute("fragment", "product");
        model.addAttribute("script", "product");
        model.addAttribute("activeIdForActivePage", "activeProductPage");

        return USER_PAGE;
    }

    @GetMapping("/{productId}/details/page")
    public String productDetailsPage(@PathVariable("productId") Long productId, Model model){
        Product product = productService.getProduct(productId).getData();

        model.addAttribute("title", "Product Details");
        model.addAttribute("folder", "product");
        model.addAttribute("fragment", "productDetails");
        model.addAttribute("script", "productDetails");

        model.addAttribute("product", product);

        return USER_PAGE;
    }
}
