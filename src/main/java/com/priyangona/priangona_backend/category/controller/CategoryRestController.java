package com.priyangona.priangona_backend.category.controller;

import com.priyangona.priangona_backend.category.model.Category;
import com.priyangona.priangona_backend.category.service.CategoryService;
import com.priyangona.priangona_backend.common.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping()
    public Response<List<Category>> getCategory(){
        return categoryService.getCategory();
    }

    @GetMapping("/{categoryId}")
    public Response<Category> getCategory(@PathVariable("categoryId") Long categoryId){
        return categoryService.getCategory(categoryId);
    }
}


