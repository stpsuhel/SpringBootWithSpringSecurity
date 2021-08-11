package com.priyangona.priangona_backend.category.service;

import com.priyangona.priangona_backend.category.model.Category;
import com.priyangona.priangona_backend.category.repository.CategoryRepository;
import com.priyangona.priangona_backend.image.model.Image;
import com.priyangona.priangona_backend.image.service.ImageService;
import com.priyangona.priangona_backend.common.Response;
import com.priyangona.priangona_backend.util.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.priyangona.priangona_backend.common.PagingUtil.*;
import static com.priyangona.priangona_backend.common.ResponseObject.*;
import static com.priyangona.priangona_backend.util.Constant.CATEGORY_IMAGE_PATH;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ImageService imageService;

    @Override
    public Response<Category> saveCategory(Category category, BindingResult bindingResult, MultipartFile image) {
        Response<Category> response = new Response<>();

        if (imageService.imageNotNull(image)) {
            Response<Image> saveImage = imageService.saveImage(image, CATEGORY_IMAGE_PATH);
            if (saveImage.isSuccess()) {
                category.setImageName(saveImage.getData());
            }
        }
        category.setCreatedBy("Suhel");
        category.setCreatedById(1L);
        category.setUpdatedBy("Suhel");
        category.setUpdatedById(1L);

        if (bindingResult.hasErrors()){
            return bindingResultErrorResponse(response, bindingResult.getAllErrors());
        }
        return createSuccessResponse(response, categoryRepository.save(category));
    }

    @Override
    public Response<Category> updateCategory(Category category, BindingResult bindingResult, MultipartFile image) {
        Response<Category> response = new Response<>();

        if (imageService.imageNotNull(image)) {
            Response<Image> saveImage = imageService.saveImage(image, CATEGORY_IMAGE_PATH);
            if (saveImage.isSuccess()) {
                category.setImageName(saveImage.getData());
            }
        }
        category.setUpdatedBy("Suhel");
        category.setUpdatedById(1L);

        if (bindingResult.hasErrors()){
            bindingResultErrorResponse(response, bindingResult.getAllErrors());
        }
        return updateSuccessResponse(response, categoryRepository.save(category));
    }

    @Override
    public Response<Boolean> deleteCategory(Long categoryId) {
        Response<Boolean> response = new Response<>();
        if (isIdNotValid(categoryId)){
            return noIdFoundResponse(response);
        }
        Optional<Category> optionalCategory = categoryRepository.findByIdAndStatus(categoryId, Status.Active.value);
        if (optionalCategory.isEmpty()){
            return noDataFoundResponse(response);
        }
        Category category = optionalCategory.get();
        category.setStatus(Status.DeActive.value);
        categoryRepository.save(category);

        return dataDeletedSuccessResponse(response);
    }

    @Override
    public Response<List<Category>> getCategory() {
        Response<List<Category>> response = new Response<>();
        List<Category> categoryList = categoryRepository.findAllByStatus(Status.Active.value);
        if (categoryList.isEmpty()){
            return noDataFoundResponse(response);
        }
        return dataFoundSuccessResponse(response, categoryList);
    }

    @Override
    public Response<Category> getCategory(Long categoryId) {
        Response<Category> response = new Response<>();
        if (isIdNotValid(categoryId)){
            return noIdFoundResponse(response);
        }
        Optional<Category> category = categoryRepository.findByIdAndStatus(categoryId, Status.Active.value);
        if (category.isEmpty() || !category.get().getId().equals(categoryId)){
            return noDataFoundResponse(response);
        }
        return dataFoundSuccessResponse(response, category.get());
    }

    @Override
    public Response<List<Category>> getCategoryWithoutStatus() {
        Response<List<Category>> response = new Response<>();
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()){
            return noDataFoundResponse(response);
        }
        return dataFoundSuccessResponse(response, categoryList);
    }

    @Override
    public Response<Category> getCategoryWithoutStatus(Long categoryId) {
        Response<Category> response = new Response<>();
        if (isIdNotValid(categoryId)){
            return noIdFoundResponse(response);
        }
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty() || !category.get().getId().equals(categoryId)){
            return noDataFoundResponse(response);
        }
        return dataFoundSuccessResponse(response, category.get());
    }

    @Override
    public Response<Category> setActiveCategory(Long categoryId) {
        Response<Category> response = new Response<>();
        if (isIdNotValid(categoryId)){
            return noIdFoundResponse(response);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty() || !optionalCategory.get().getId().equals(categoryId)){
            return noDataFoundResponse(response);
        }
        Category category = optionalCategory.get();
        category.setStatus(Status.Active.value);

        return dataFoundSuccessResponse(response, categoryRepository.save(category));
    }
}
