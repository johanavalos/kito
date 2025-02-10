package com.example.hiber_api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.hiber_api.dto.UserDTO;
import com.example.hiber_api.exception.PictureUploadFailException;
import com.example.hiber_api.model.security.User;
import com.example.hiber_api.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private S3Service s3Service;

    @Value("${spring.destination.folder}")
    String staticFolderPath;

    @Value("${aws.s3.profile-picture.bucket-name}")
    String bucketName;

    public List<UserDTO> getAll(){
        return userRepository.findBy();
    }

    public void deleteById(Long id) {     
        userRepository.deleteUser(id);
    }

    public void deleteSelf() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user;
        try {
            user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exist."));
        } catch (UsernameNotFoundException e) {return;}
        
        userRepository.deleteUser(user.getId());
    }

    public Boolean updateProfilePicture(MultipartFile picture) throws IOException {
        try {
            Path staticDir = Paths.get(staticFolderPath);
            if (!Files.exists(staticDir)) {
                Files.createDirectories(staticDir);
            }

            Path picturePath = staticDir.resolve(picture.getOriginalFilename());
            picturePath = Files.write(picturePath, picture.getBytes());
            
            //TODO Encode key
            String key = SecurityContextHolder.getContext().getAuthentication().getName();

            Boolean uploadSuccess = false;

            uploadSuccess = this.s3Service.uploadFile(bucketName, key, picturePath);

            Files.delete(picturePath);
            
            if(!uploadSuccess){
                throw new PictureUploadFailException();
            }

            return uploadSuccess;
        } catch (IOException e) {
            throw new PictureUploadFailException();
        }
    }

    public byte[] getProfilePicture() {

        String key = SecurityContextHolder.getContext().getAuthentication().getName();

        // String picturePath = staticFolderPath + "/" + key;

        return s3Service.getFile(bucketName, key);
    }
}