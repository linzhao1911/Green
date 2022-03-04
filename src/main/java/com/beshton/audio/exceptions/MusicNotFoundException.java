package com.beshton.audio.exceptions;

public class MusicNotFoundException extends RuntimeException {

    public MusicNotFoundException(Long id) {
        super("Could not find sale " + id);
    }
}