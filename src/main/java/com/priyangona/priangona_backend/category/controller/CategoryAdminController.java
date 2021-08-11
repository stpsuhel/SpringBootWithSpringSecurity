package com.priyangona.priangona_backend.category.controller;

import com.priyangona.priangona_backend.category.model.Category;
import com.priyangona.priangona_backend.category.service.CategoryService;
import com.priyangona.priangona_backend.common.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.priyangona.priangona_backend.util.Constant.ADMIN_PAGE;

@Controller
@RequestMapping("/admin/category")
@AllArgsConstructor
public class CategoryAdminController {

    private final CategoryService categoryService;

    @GetMapping("/page")
    public String categoryPage(Model model){

        model.addAttribute("title", "Category");
        model.addAttribute("folder", "category");
        model.addAttribute("fragment", "adminCategory");
        model.addAttribute("script", "adminCategory");

        return ADMIN_PAGE;
    }

    @PostMapping
    @ResponseBody
    public Response<Category> saveCategory(
            @Validated @ModelAttribute Category category,
            BindingResult bindingResult,
            @RequestPart("image") MultipartFile image
    ) {
        return categoryService.saveCategory(category, bindingResult, image);
    }

    @PutMapping
    @ResponseBody
    public Response<Category> updateCategory(
            @Validated @ModelAttribute Category category,
            BindingResult bindingResult,
            @RequestPart("image") MultipartFile image
    ){
        return categoryService.updateCategory(category, bindingResult, image);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Response<Boolean> deleteCategory(@PathVariable("id") Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping
    @ResponseBody
    public Response<List<Category>> getCategoryWithoutStatus(){
        return categoryService.getCategoryWithoutStatus();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Response<Category> getCategoryWithoutStatus(@PathVariable("id") Long categoryId){
        return categoryService.getCategoryWithoutStatus(categoryId);
    }

    @PutMapping("/active")
    @ResponseBody
    public Response<Category> setActiveCategory(@ModelAttribute("id") Long categoryId){
        return categoryService.setActiveCategory(categoryId);
    }
}
