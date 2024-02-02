package org.example.shoppingcartapi.repository;

/**
 * @author Daniél Garrido
 */
public interface EntityRepository<T> {

    T getByIdSimple(Class<T> tClass, Long id);
}
