package org.example.shoppingcartapi.service.impl;

import org.example.shoppingcartapi.entity.Cart;
import org.example.shoppingcartapi.entity.Item;
import org.example.shoppingcartapi.repository.CartRepository;
import org.example.shoppingcartapi.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniél Garrido
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> findCart(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<Item> findAllItems(Cart cart) {
        return cart.getItems();
    }

    @Override
    public void addItem(Cart cart, Item item) {

    }

    @Override
    public void removeItem(Cart cart, Item item) {

    }
}
