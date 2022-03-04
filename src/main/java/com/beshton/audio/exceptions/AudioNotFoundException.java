package com.beshton.audio.exceptions;

public class AudioNotFoundException extends RuntimeException {

    public AudioNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}