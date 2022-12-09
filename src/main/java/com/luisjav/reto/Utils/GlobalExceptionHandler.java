package com.luisjav.reto.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.luisjav.reto.Exception.NoContentException;
import com.luisjav.reto.Exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> validationException(){        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoContentException.class})
    public ResponseEntity<?> genericException(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
