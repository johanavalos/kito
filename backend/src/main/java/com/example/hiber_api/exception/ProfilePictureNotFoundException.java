package com.example.hiber_api.exception;

public class ProfilePictureNotFoundException extends RuntimeException  {
    public ProfilePictureNotFoundException(){
        super("Could not find profile picture");
    }
}
