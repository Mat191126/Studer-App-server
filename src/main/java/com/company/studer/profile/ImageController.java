package com.company.studer.profile;

import com.company.studer.common.helper.FileUploadUtil;
import com.company.studer.profile.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class ImageController {

    @PostMapping("/photo/upload")
    public ResponseEntity<HttpStatus> saveImage(User user,
                                                @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = UUID.randomUUID().toString() + ".jpg";
        user.setPhoto(fileName);

        String uploadDir = "profile-images/";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return ResponseEntity
                .ok()
                .body(HttpStatus.CREATED);
    }

    @GetMapping(value = "/photo/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {

        BufferedImage image;
        image = ImageIO.read(new File("profile-images/" + imageName + ".jpg"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (image != null) {
            ImageIO.write(image, "jpg", byteArrayOutputStream);
        }
        byte[] bytes = byteArrayOutputStream.toByteArray();

        return ResponseEntity
                .ok()
                .body(bytes);
    }
}
