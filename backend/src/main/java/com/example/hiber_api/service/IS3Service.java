package com.example.hiber_api.service;

import java.nio.file.Path;

public interface IS3Service {

    public Boolean uploadFile(String bucketName, String key, Path filePath);

}
