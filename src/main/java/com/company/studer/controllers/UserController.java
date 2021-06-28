package com.company.studer.controllers;

import com.company.studer.entities.User;
import com.company.studer.helper.FileUploadUtil;
import com.company.studer.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private Iterable<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private User getById(@PathVariable UUID id) {
        return userService.get(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    protected User addNewObject(@RequestBody User user) {
        if (userService.add(user)) {
            return user;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    protected void updateObject(@RequestBody User user, @PathVariable UUID id) {
        if (!user.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (!userService.update(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    protected void deleteObject(@PathVariable UUID id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


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
