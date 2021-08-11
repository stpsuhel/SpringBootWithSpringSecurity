package com.priyangona.priangona_backend.category.repository;

import com.priyangona.priangona_backend.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByStatus(int status);
    Optional<Category> findByIdAndStatus(Long id, int status);
}
