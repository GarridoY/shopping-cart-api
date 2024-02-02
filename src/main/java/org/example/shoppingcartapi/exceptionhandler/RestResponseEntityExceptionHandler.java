package org.example.shoppingcartapi.exceptionhandler;

import jakarta.persistence.EntityNotFoundException;
import org.example.shoppingcartapi.exception.EntityNotFoundInListException;
import org.example.shoppingcartapi.response.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author Daniél Garrido
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={ EntityNotFoundException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, LocalDateTime.now(), "Entity was not found.", ex.getMessage()));
    }

    @ExceptionHandler(value={ EntityNotFoundInListException.class })
    protected ResponseEntity<Object> handleNotFoundInListConflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
