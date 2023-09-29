package com.saludo.api.exceptions.handler;

import com.saludo.api.exceptions.dtos.ErrorResponseDTO;
import com.saludo.api.exceptions.exceptionKinds.ContactAlreadyExistException;
import com.saludo.api.exceptions.exceptionKinds.NonExistentContactException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> defaultErrorHandler(HttpServletRequest req, Exception e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .ok(false)
                        .message(e.getMessage())
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ContactAlreadyExistException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> defaultErrorHandler(HttpServletRequest req, ContactAlreadyExistException e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .ok(false)
                        .message(e.getMessage())
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build()
                , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NonExistentContactException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> defaultErrorHandler(HttpServletRequest req, NonExistentContactException e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .ok(false)
                        .message(e.getMessage())
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build()
                , HttpStatus.NOT_FOUND);
    }


}
