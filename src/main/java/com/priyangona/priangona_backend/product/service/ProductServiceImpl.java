package com.priyangona.priangona_backend.product.service;

import com.priyangona.priangona_backend.category.model.Category;
import com.priyangona.priangona_backend.category.service.CategoryService;
import com.priyangona.priangona_backend.image.model.Image;
import com.priyangona.priangona_backend.image.service.ImageService;
import com.priyangona.priangona_backend.paging.Paging;
import com.priyangona.priangona_backend.paging.PagingRequest;
import com.priyangona.priangona_backend.product.model.Product;
import com.priyangona.priangona_backend.product.model.ProductDTO;
import com.priyangona.priangona_backend.product.repository.ProductRepository;
import com.priyangona.priangona_backend.common.Response;
import com.priyangona.priangona_backend.util.Status;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.priyangona.priangona_backend.common.PagingUtil.*;
import static com.priyangona.priangona_backend.common.ResponseObject.*;
import static com.priyangona.priangona_backend.product.model.ProductDTO.dtoToProduct;
import static com.priyangona.priangona_backend.util.Constant.*;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @Override
    public Response<Product> saveProduct(
            ProductDTO productDTO, BindingResult bindingResult,
            MultipartFile image, MultipartFile[] secondaryImages
    ) {
        Response<Product> response = new Response<>();
        Product product = getProductFromProductDTO(productDTO, new Product());

        if (imageService.imageNotNull(image)) {
            Response<Image> saveImage = imageService.saveImage(image, PRODUCT_IMAGE_PATH);

            if (saveImage.isSuccess()) {
                product.setMainImage(saveImage.getData());
            }
        }else{
            bindingResult.rejectValue("mainImage", "500", "Select Image for Product");
        }
        if (bindingResult.hasErrors()){
            return bindingResultErrorResponse(response, bindingResult.getAllErrors());
        }

        if (secondaryImages.length > 0){
            List<Image> imageList = new ArrayList<>();
            for (MultipartFile secondaryImage : secondaryImages) {
                if (imageService.imageNotNull(secondaryImage)) {
                    Response<Image> saveImage = imageService.saveImage(image, PRODUCT_IMAGE_PATH);

                    if (saveImage.isSuccess()) {
                        imageList.add(saveImage.getData());
                    }
                }
            }
            product.setSecondaryImages(imageList);
        }
        product.setCreatedBy("Suhel");
        product.setCreatedById(1L);
        product.setUpdatedBy("Suhel");
        product.setUpdatedById(1L);

        return createSuccessResponse(response, productRepository.save(product));
    }

    @Override
    public Response<Product> updateProduct(
            ProductDTO productDTO, BindingResult bindingResult,
            MultipartFile image, MultipartFile[] secondaryImages
    ) {
        Response<Product> response = new Response<>();
        Optional<Product> optionalProduct = productRepository.findByIdAndStatus(productDTO.getId(), Status.Active.value);

        if (optionalProduct.isEmpty()){
            return noDataFoundResponse(response);
        }
        Product product = getProductFromProductDTO(productDTO, optionalProduct.get());

        if (imageService.imageNotNull(image)) {
            Response<Image> saveImage = imageService.saveImage(image, CATEGORY_IMAGE_PATH);
            if (saveImage.isSuccess()) {
                product.setMainImage(saveImage.getData());
            }
        }
        if (bindingResult.hasErrors()){
            return bindingResultErrorResponse(response, bindingResult.getAllErrors());
        }

        product.setUpdatedBy("Suhel");
        product.setUpdatedById(1L);

        return updateSuccessResponse(response, productRepository.save(product));
    }

    @Override
    public Response<Boolean> deleteProduct(Long productId) {
        Response<Boolean> response = new Response<>();
        if (isIdNotValid(productId)){
            return noIdFoundResponse(response);
        }
        Optional<Product> optionalProduct = productRepository.findByIdAndStatus(productId, Status.Active.value);
        if (optionalProduct.isEmpty()){
            return noDataFoundResponse(response);
        }
        Product product = optionalProduct.get();
        product.setStatus(Status.DeActive.value);
        productRepository.save(product);
        return dataDeletedSuccessResponse(response);
    }

    @Override
    public Paging<Product> getProductWithoutStatus(PagingRequest pagingRequest) {
        if (isPagingRequestDataNotValid(pagingRequest)){
            return new Paging<>(pagingRequest.getDraw());
        }
        Pageable pageable = getPageable(pagingRequest);
        long totalRecords = getTotalRecords(false);

        if (!pagingRequest.getSearch().getValue().isEmpty()){
            return new Paging<>(
                    productRepository.findBySearchValue(pageable, pagingRequest.getSearch().getValue()),
                    pagingRequest.getDraw(), totalRecords
            );
        }
        return new Paging<>(productRepository.findAll(pageable), pagingRequest.getDraw(), totalRecords);
    }

    @Override
    public Response<Product> getProductWithoutStatus(Long productId) {
        Response<Product> response = new Response<>();
        if (isIdNotValid(productId)){
            return noIdFoundResponse(response);
        }
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty() || !product.get().getId().equals(productId)){
            return noDataFoundResponse(response);
        }
        return dataFoundSuccessResponse(response, product.get());
    }

    @Override
    public Paging<Product> getProduct(PagingRequest pagingRequest) {
        if (isPagingRequestDataNotValid(pagingRequest)){
            return new Paging<>(pagingRequest.getDraw());
        }
        Pageable pageable = getPageable(pagingRequest);
        long totalRecords = getTotalRecords(true);

        if (pagingRequest.getSearch() != null && !pagingRequest.getSearch().getValue().isEmpty()){
            return new Paging<>(
                    productRepository.findBySearchValue(pageable, pagingRequest.getSearch().getValue()),
                    pagingRequest.getDraw(), totalRecords
            );
        }
        return new Paging<>(
                productRepository.findAllByStatus(pageable, Status.Active.value),
                pagingRequest.getDraw(), totalRecords
        );
    }

    @Override
    public Response<Product> getProduct(Long productId) {
        Response<Product> response = new Response<>();
        if (isIdNotValid(productId)){
            return noIdFoundResponse(response);
        }
        Optional<Product> product = productRepository.findByIdAndStatus(productId, Status.Active.value);
        if (product.isEmpty() || !product.get().getId().equals(productId)){
            return noDataFoundResponse(response);
        }
        return dataFoundSuccessResponse(response, product.get());
    }

    @Override
    public List<Category> getCategoryDTO() {
        return categoryService.getCategory().getData();
    }

    @Override
    public Response<Product> setActiveProduct(Long productId) {
        Response<Product> response = new Response<>();
        if (isIdNotValid(productId)){
            return noIdFoundResponse(response);
        }
        Optional<Product> optionalCategory = productRepository.findById(productId);
        if (optionalCategory.isEmpty() || !optionalCategory.get().getId().equals(productId)){
            return noDataFoundResponse(response);
        }
        Product product = optionalCategory.get();
        product.setStatus(Status.Active.value);
        return dataFoundSuccessResponse(response, productRepository.save(product));
    }

    private Product getProductFromProductDTO(ProductDTO productDTO, Product product){
        Category category = new Category();
        if (productDTO.getCategory() != null && productDTO.getCategory() >= 0){
            category = categoryService.getCategory(productDTO.getCategory()).getData();
        }
        return dtoToProduct(productDTO, category, product);
    }

    private Long getTotalRecords(boolean isActive){
        if (isActive){
            return productRepository.countAllByStatus(Status.Active.value);
        }
        return productRepository.count();
    }
}
