package com.beshton.audio.exceptions;

public class SoundNotFoundException extends RuntimeException {

    public SoundNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}