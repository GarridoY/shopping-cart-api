package org.example.shoppingcartapi.exception;

/**
 * @author Daniél Garrido
 */
public class EntityNotFoundInListException extends RuntimeException {

    public EntityNotFoundInListException(String message) {
        super(message);
    }
}
