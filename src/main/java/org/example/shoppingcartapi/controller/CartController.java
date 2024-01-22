package org.example.shoppingcartapi.controller;

import org.example.shoppingcartapi.entity.Cart;
import org.example.shoppingcartapi.repository.CartRepository;
import org.example.shoppingcartapi.entity.Item;
import org.example.shoppingcartapi.repository.ItemRepository;
import org.example.shoppingcartapi.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniél Garrido
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    CartController(CartService cartService, CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/{id}/items")
    ResponseEntity<List<Item>> allItemsInCart(@PathVariable Long id) {
        Optional<Cart> cart = cartService.findCart(id);
        if (cart.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(cartService.findAllItems(cart.get()));
    }

    @PostMapping("/{id}/items")
    ResponseEntity<Object> addItem(@RequestBody Item item, @PathVariable Long id) {
        Optional<Cart> cartOpt = cartRepository.findById(id);
        if (cartOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Cart cart = cartOpt.get();
        cart.getItems().add(item);

        cartRepository.save(cart);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/items/{itemId}")
    ResponseEntity<Object> deleteItem(@PathVariable Long id, @PathVariable Long itemId) {
        Optional<Cart> cartOpt = cartRepository.findById(id);
        if (cartOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if (itemOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Cart cart = cartOpt.get();
        Item item = itemOpt.get();
        if (!cart.getItems().contains(item)) {
            return ResponseEntity.badRequest().build();
        }

        cart.getItems().remove(item);
        cartRepository.save(cart);
        return ResponseEntity.ok().build();
    }
}
