package com.example.hiber_api.exception;

public class PictureUploadFailException extends RuntimeException {
    public PictureUploadFailException(){
        super("Could not upload picture");
    }
}
