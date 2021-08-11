package com.priyangona.priangona_backend.product.model;

import com.priyangona.priangona_backend.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Product Name cannot be empty")
    @Size(min = 3, max = 50, message = "Size must be between 3 and 50")
    private String name;

    @Nullable
    @Size(min = 3, message = "Size must be greater then 3")
    private String description;

    @NotNull(message = "Price should be greater then 0")
    private int price;
    private int discount;

    @NotNull(message = "Stock should be greater then 0")
    private int stock;

    @NotNull(message = "Color should not be null")
    private String color;

    @NotNull(message = "Select one category")
    private Long category;

    private String mainImage;

    public static Product dtoToProduct(ProductDTO productDTO, Category category, Product product){
        product.setName(productDTO.name);
        product.setDescription(productDTO.description);
        product.setCategory(category);
        product.setPrice(productDTO.price);
        product.setDiscount(productDTO.discount);
        product.setStock(productDTO.stock);
        product.setColor(productDTO.color);

        return product;
    }
}
