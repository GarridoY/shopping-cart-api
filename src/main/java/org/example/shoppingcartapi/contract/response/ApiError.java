package org.example.shoppingcartapi.contract.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Daniél Garrido
 */
public record ApiError(HttpStatus status,
                       LocalDateTime timestamp,
                       String message,
                       String debugMessage) {
}
