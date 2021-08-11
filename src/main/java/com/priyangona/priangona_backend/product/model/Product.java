package com.priyangona.priangona_backend.product.model;

import com.fasterxml.jackson.annotation.*;
import com.priyangona.priangona_backend.category.model.Category;
import com.priyangona.priangona_backend.image.model.Image;
import com.priyangona.priangona_backend.util.EditInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
public class Product extends EditInfo {

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

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Select one category")
    private Category category;

    @OneToOne
    private Image mainImage;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Image> secondaryImages;

//    public Product(ProductDTO productDTO, Category category) {
//        this.name = productDTO.getName();
//        this.description = productDTO.getDescription();
//        this.price = productDTO.getPrice();
//        this.discount = productDTO.getDiscount();
//        this.stock = productDTO.getStock();
//        this.color = productDTO.getColor();
//        this.category = category;
//    }

    @Override
    public String toString() {
        return "Product{" + super.toString() +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", stock=" + stock +
                ", category=" + category +
                ", mainImage='" + mainImage + '\'' +
                '}';
    }
}
