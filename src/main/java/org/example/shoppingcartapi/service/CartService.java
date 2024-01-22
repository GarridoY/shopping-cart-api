package org.example.shoppingcartapi.service;

import org.example.shoppingcartapi.entity.Cart;
import org.example.shoppingcartapi.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniél Garrido
 */
@Service
public interface CartService {

    Optional<Cart> findCart(Long id);

    List<Item> findAllItems(Cart cart);
    void addItem(Cart cart, Item item);
    void removeItem(Cart cart, Item item);
}
