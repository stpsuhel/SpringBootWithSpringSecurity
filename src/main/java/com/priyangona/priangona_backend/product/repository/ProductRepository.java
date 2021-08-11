package com.priyangona.priangona_backend.product.repository;

import com.priyangona.priangona_backend.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByStatus(Pageable pageable, int status);
    Optional<Product> findByIdAndStatus(Long productId, int status);

    @Query("select p from Product p where lower(p.name) like lower(concat('%', :name, '%'))")
    Page<Product> findBySearchValue(Pageable pageable, @Param("name") String name);

    Long countAllByStatus(int status);
}
