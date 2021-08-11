package com.priyangona.priangona_backend.product.controller;

import com.priyangona.priangona_backend.paging.Paging;
import com.priyangona.priangona_backend.product.model.Product;
import com.priyangona.priangona_backend.product.model.ProductDTO;
import com.priyangona.priangona_backend.product.service.ProductService;
import com.priyangona.priangona_backend.paging.PagingRequest;
import com.priyangona.priangona_backend.common.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

import static com.priyangona.priangona_backend.util.Constant.ADMIN_PAGE;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/product")
//@PreAuthorize("ADMIN")
public class ProductAdminController {
    private final ProductService productService;

    @GetMapping("/page")
    public String productPage(Model model){
        model.addAttribute("categoryList", productService.getCategoryDTO());

        model.addAttribute("title", "Product");
        model.addAttribute("folder", "product");
        model.addAttribute("fragment", "adminProduct");
        model.addAttribute("script", "adminProduct");

        return ADMIN_PAGE;
    }

    @PostMapping
    @ResponseBody
    public Response<Product> saveProduct(
            @Validated @ModelAttribute ProductDTO productDTO,
            BindingResult bindingResult,
            @RequestPart("image") MultipartFile image,
            @RequestPart("image") MultipartFile[] secondaryImages
    ) {
        System.out.println(productDTO);
        System.out.println(image);
        System.out.println(Arrays.toString(secondaryImages));
        return productService.saveProduct(productDTO, bindingResult, image, secondaryImages);
    }

    @PutMapping
    @ResponseBody
    public Response<Product> updateProduct(
            @Validated @ModelAttribute ProductDTO productDTO,
            BindingResult bindingResult,
            @RequestPart("image") MultipartFile image,
            @RequestPart("image") MultipartFile[] secondaryImages
    ){
        return productService.updateProduct(productDTO, bindingResult, image, secondaryImages);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Response<Boolean> deleteProduct(@PathVariable("id") Long productId){
        return productService.deleteProduct(productId);
    }

    @PostMapping("/all")
    @ResponseBody
    public Paging<Product> getProductWithoutStatus(@RequestBody PagingRequest pagingRequest){
        System.out.println(pagingRequest);
        System.out.println(pagingRequest.getColumns().get(pagingRequest.getOrder().get(0).getColumn()).getData());
        return productService.getProductWithoutStatus(pagingRequest);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Response<Product> getProductWithoutStatus(@PathVariable("id") Long productId){
        return productService.getProductWithoutStatus(productId);
    }

    @PutMapping("/active")
    @ResponseBody
    public Response<Product> setActiveProduct(@ModelAttribute("id") Long productId){
        return productService.setActiveProduct(productId);
    }
}
