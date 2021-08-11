package com.priyangona.priangona_backend.image.service;

import com.priyangona.priangona_backend.common.Response;
import com.priyangona.priangona_backend.image.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Response<Image> saveImage(MultipartFile file, String path);
    boolean imageNotNull(MultipartFile image);
}
