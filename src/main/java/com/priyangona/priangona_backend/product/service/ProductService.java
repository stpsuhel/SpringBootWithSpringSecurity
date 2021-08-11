package com.priyangona.priangona_backend.product.service;

import com.priyangona.priangona_backend.category.model.Category;
import com.priyangona.priangona_backend.paging.Paging;
import com.priyangona.priangona_backend.paging.PagingRequest;
import com.priyangona.priangona_backend.product.model.Product;
import com.priyangona.priangona_backend.product.model.ProductDTO;
import com.priyangona.priangona_backend.common.Response;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Response<Product> saveProduct(ProductDTO productDTO, BindingResult bindingResult, MultipartFile image, MultipartFile[] secondaryImages);
    Response<Product> updateProduct(ProductDTO productDTO, BindingResult bindingResult, MultipartFile image, MultipartFile[] secondaryImages);
    Response<Boolean> deleteProduct(Long productId);

    Paging<Product> getProductWithoutStatus(PagingRequest pagingRequest);
    Response<Product> getProductWithoutStatus(Long productId);

    Paging<Product> getProduct(PagingRequest pagingRequest);
    Response<Product> getProduct(Long productId);

    List<Category> getCategoryDTO();

    Response<Product> setActiveProduct(Long productId);
}
