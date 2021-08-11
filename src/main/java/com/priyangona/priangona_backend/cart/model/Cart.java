package com.priyangona.priangona_backend.cart.model;

import com.priyangona.priangona_backend.auth.User;
import com.priyangona.priangona_backend.product.model.Product;
import com.priyangona.priangona_backend.util.EditInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends EditInfo {

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> products;
}
