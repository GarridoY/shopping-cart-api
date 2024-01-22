package org.example.shoppingcartapi.repository;

import org.example.shoppingcartapi.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Daniél Garrido
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
}
