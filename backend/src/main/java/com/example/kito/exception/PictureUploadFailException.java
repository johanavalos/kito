package com.example.kito.exception;

public class PictureUploadFailException extends RuntimeException {
    public PictureUploadFailException(){
        super("Could not upload picture");
    }
}
