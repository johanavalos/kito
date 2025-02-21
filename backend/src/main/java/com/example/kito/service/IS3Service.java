package com.example.kito.service;

import java.nio.file.Path;

public interface IS3Service {

    public Boolean uploadFile(String bucketName, String key, Path filePath);

}
