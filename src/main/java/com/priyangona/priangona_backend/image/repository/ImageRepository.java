package com.priyangona.priangona_backend.image.repository;

import com.priyangona.priangona_backend.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
