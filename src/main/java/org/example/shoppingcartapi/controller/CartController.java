package org.example.shoppingcartapi.controller;

import org.example.shoppingcartapi.request.AddItemRequest;
import org.example.shoppingcartapi.response.ItemResponse;
import org.example.shoppingcartapi.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Daniél Garrido
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}/items")
    ResponseEntity<List<ItemResponse>> allItemsInCart(@PathVariable Long id) {
        List<ItemResponse> itemResponses = cartService.findAllItems(id);

        return ResponseEntity.ok(itemResponses);
    }

    @PostMapping("/{id}/items")
    ResponseEntity<Object> addItem(@RequestBody AddItemRequest addItemRequest, @PathVariable Long id) {
        cartService.addItem(id, addItemRequest.id());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/items/{itemId}")
    ResponseEntity<Object> deleteItem(@PathVariable Long id, @PathVariable Long itemId) {
        cartService.removeItem(id, itemId);

        return ResponseEntity.ok().build();
    }
}
