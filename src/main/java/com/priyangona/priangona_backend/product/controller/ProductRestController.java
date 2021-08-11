package com.priyangona.priangona_backend.product.controller;

import com.priyangona.priangona_backend.paging.Paging;
import com.priyangona.priangona_backend.paging.PagingRequest;
import com.priyangona.priangona_backend.product.model.Product;
import com.priyangona.priangona_backend.product.service.ProductService;
import com.priyangona.priangona_backend.common.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @PostMapping()
    public Paging<Product> getProduct(@RequestBody PagingRequest pagingRequest){
        return productService.getProduct(pagingRequest);
    }

    @GetMapping("/{id}")
    public Response<Product> getProduct(@PathVariable("id") Long productId){
        return productService.getProduct(productId);
    }
}
