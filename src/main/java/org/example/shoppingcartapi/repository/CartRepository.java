package org.example.shoppingcartapi.repository;

import org.example.shoppingcartapi.domain.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Daniél Garrido
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
