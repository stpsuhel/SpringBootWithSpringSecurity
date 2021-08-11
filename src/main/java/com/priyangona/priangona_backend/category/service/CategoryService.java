package com.priyangona.priangona_backend.category.service;

import com.priyangona.priangona_backend.category.model.Category;
import com.priyangona.priangona_backend.common.Response;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    Response<Category> saveCategory(Category category, BindingResult bindingResult, MultipartFile image);
    Response<Category> updateCategory(Category category, BindingResult bindingResult, MultipartFile image);
    Response<Boolean> deleteCategory(Long categoryId);

    Response<List<Category>> getCategory();
    Response<Category> getCategory(Long categoryId);

    Response<List<Category>> getCategoryWithoutStatus();
    Response<Category> getCategoryWithoutStatus(Long categoryId);

    Response<Category> setActiveCategory(Long categoryId);
}
