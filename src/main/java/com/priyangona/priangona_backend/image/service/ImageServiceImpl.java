package com.priyangona.priangona_backend.image.service;

import com.priyangona.priangona_backend.common.Response;
import com.priyangona.priangona_backend.image.model.Image;
import com.priyangona.priangona_backend.image.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import static com.priyangona.priangona_backend.util.Constant.ROOT_PATH;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService{
    private final ImageRepository imageRepository;

    @Override
    public Response<Image> saveImage(MultipartFile file, String path) {
        Response<Image> response = new Response<>();

        if (!imageNotNull(file)){
            response.setSuccess(false);
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            response.setMessage("File is Empty or not readable");
            return response;
        }
        String filename = file.getOriginalFilename();
        assert filename != null;

        String fileExtension = getFileExtension(filename);
        long dateInMilliseconds = new Date().getTime();

        String fileFullName = "IMG" +  dateInMilliseconds + "." + fileExtension;
        Path filePath = Paths.get(ROOT_PATH + path + fileFullName).toAbsolutePath();
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            response.setSuccess(false);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to save file. try again!");
            return response;
        }

        response.setSuccess(true);
        response.setData(saveImageDataToDatabase(path + fileFullName));
        return response;
    }

    @Override
    public boolean imageNotNull(MultipartFile image) {
        return image != null && !image.isEmpty();
    }

    private Image saveImageDataToDatabase(String imageName){
        return imageRepository.save(new Image(imageName));
    }

    private String getFileExtension(String fileName){
        String[] split = fileName.split("\\.");
        return split[split.length-1];
    }
}
