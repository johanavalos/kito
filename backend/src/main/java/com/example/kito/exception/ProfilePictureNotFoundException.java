package com.example.kito.exception;

public class ProfilePictureNotFoundException extends RuntimeException  {
    public ProfilePictureNotFoundException(){
        super("Could not find profile picture");
    }
}
