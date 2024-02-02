package org.example.shoppingcartapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.shoppingcartapi.entity.Cart;
import org.example.shoppingcartapi.entity.Item;
import org.example.shoppingcartapi.exception.EntityNotFoundInListException;
import org.example.shoppingcartapi.mapper.ItemMapper;
import org.example.shoppingcartapi.repository.CartRepository;
import org.example.shoppingcartapi.repository.ItemRepository;
import org.example.shoppingcartapi.response.ItemResponse;
import org.example.shoppingcartapi.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Daniél Garrido
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    public CartServiceImpl(CartRepository cartRepository, ItemMapper itemMapper, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemResponse> findAllItems(Long cartId) {
        return cartRepository.getByIdSimple(Cart.class, cartId).getItems().stream()
                .map(itemMapper)
                .collect(Collectors.toList());
    }

    @Override
    public void addItem(Long cartId, Long itemId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        Item item = itemRepository.getByIdSimple(Item.class, itemId);

        cart.getItems().add(item);
        cartRepository.save(cart);
    }

    @Override
    public void removeItem(Long cartId, Long itemId) {
        Cart cart = cartRepository.getByIdSimple(Cart.class, cartId);
        Item item = itemRepository.getByIdSimple(Item.class, itemId);

        if (!cart.getItems().contains(item)) {
            throw new EntityNotFoundInListException("Item was not found in cart");
        }

        cart.getItems().remove(item);
        cartRepository.save(cart);
    }
}
