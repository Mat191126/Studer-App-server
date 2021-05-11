package com.company.studer.controllers;

import com.company.studer.entities.User;
import com.company.studer.helper.FileUploadUtil;
import com.company.studer.services.UserService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView saveImage(User user,
                                 @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = UUID.randomUUID().toString();
        user.setPhoto(fileName);

        String uploadDir = "static/profile-images/" + user.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/photo/load", true);
    }

    @GetMapping(value = "/photo/{imageName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {

        ClassPathResource imgFile = new ClassPathResource("static/profile-images/" + imageName + ".jpg");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .body(bytes);
    }

}
