package com.mercadolivre.mercadolivre.Validator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
public class CustomErrorHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(ConstraintViolationException exception,
                                                   ServletWebRequest webRequest) throws IOException {
        webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}

//@ControllerAdvice
//public class CustomErrorHandler {
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<CustomError> handleConstraintViolationException(ConstraintViolationException exception) {
//        CustomError customError = new CustomError();
//        customError.setStatus(HttpStatus.BAD_REQUEST);
//        customError.setMessage(exception.getMessage());
//        customError.addConstraintErrors(exception.getConstraintViolations());
//        return ResponseEntity.badRequest().body(customError);
//    }
//}