package com.priyangona.priangona_backend.category.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.priyangona.priangona_backend.image.model.Image;
import com.priyangona.priangona_backend.product.model.Product;
import com.priyangona.priangona_backend.util.EditInfo;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends EditInfo {

    @NotBlank(message = "Category Name cannot be empty")
    @Size(min = 3, max = 50, message = "Size must be between 3 and 50")
    private String name;

    @Nullable
    @Size(min = 3, message = "Size must be greater then 3")
    private String description;

    @JsonBackReference
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;

    @Nullable
    @OneToOne
    private Image imageName;

    @Override
    public String toString() {
        return "Category{" + super.toString() +
                "name=" + name +
                ", description=" + description +
                ", imageName=" + imageName +
                '}';
    }
}
