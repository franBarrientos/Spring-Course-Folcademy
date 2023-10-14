package com.saludo.api.exceptions.handler;

import com.saludo.api.exceptions.dtos.ErrorResponseDTO;
import com.saludo.api.exceptions.exceptionKinds.BadRequestException;
import com.saludo.api.exceptions.exceptionKinds.ContactAlreadyExistException;
import com.saludo.api.exceptions.exceptionKinds.NonExistentContactException;
import com.saludo.api.exceptions.exceptionKinds.NotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ContactAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDTO> defaultErrorHandler(ContactAlreadyExistException e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .ok(false)
                        .message(e.getMessage())
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonExistentContactException.class)
    public ResponseEntity<ErrorResponseDTO> defaultErrorHandler(NonExistentContactException e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .ok(false)
                        .message(e.getMessage())
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build()
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorResponseDTO> notFound(NotFound e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .ok(false)
                        .message(e.getMessage())
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build()
                , HttpStatus.NOT_FOUND);
    }
  @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> badRequest(BadRequestException e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .ok(false)
                        .message(e.getMessage())
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build()
                , HttpStatus.BAD_REQUEST);
    }
     @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> badCredentials(BadCredentialsException e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .ok(false)
                        .message(e.getMessage())
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build()
                , HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> defaultErrorHandler(Exception e) {
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .ok(false)
                        .message(e.getMessage())
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
