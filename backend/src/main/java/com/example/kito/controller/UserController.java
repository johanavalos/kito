package com.example.kito.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.kito.dto.UserDTO;
import com.example.kito.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }

    @DeleteMapping("/me")
    public void deleteSelf(){
        userService.deleteSelf();
    }

    @PutMapping("/me/profile-picture")
    public Boolean updateProfilePicture(@RequestPart MultipartFile picture) throws IOException {
        return userService.updateProfilePicture(picture);
    }

    @GetMapping("/me/profile-picture")
    public ResponseEntity<byte[]> getProfilePicture() throws IOException {
        byte [] image = userService.getProfilePicture();
        return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_JPEG)
            .body(image);
    }
}
