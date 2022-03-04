package com.beshton.audio.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.beshton.audio.exceptions.*;


@ControllerAdvice
public class MusicNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(MusicNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String saleNotFoundHandler(MusicNotFoundException ex) {
        return ex.getMessage();
    }
}
