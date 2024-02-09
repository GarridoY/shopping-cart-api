package org.example.shoppingcartapi.unit.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.shoppingcartapi.contract.response.ItemResponse;
import org.example.shoppingcartapi.domain.Cart;
import org.example.shoppingcartapi.domain.Item;
import org.example.shoppingcartapi.exception.EntityNotFoundInListException;
import org.example.shoppingcartapi.repository.CartRepository;
import org.example.shoppingcartapi.repository.ItemRepository;
import org.example.shoppingcartapi.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DataJpaTest
@ComponentScan({
        "org.example.shoppingcartapi.service",
        "org.example.shoppingcartapi.mapper"
})
public class CartServiceUnitTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;
    @MockBean
    private ItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        Item item1 = new Item("Tofu");
        Item item2 = new Item("Sandwich");
        Item item3 = new Item("Pizza");

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        Cart cart = new Cart(items);

        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item1));
        when(itemRepository.findById(2L)).thenReturn(Optional.of(item2));
        when(itemRepository.findById(3L)).thenReturn(Optional.of(item3));
    }

    @Test
    void findAllItemsTest() {
        List<ItemResponse> itemResponses = cartService.findAllItems(1L);
        assertThat(itemResponses.size()).isEqualTo(2);
        assertThat(itemResponses.get(0).name()).isEqualTo("Tofu");
        assertThat(itemResponses.get(1).name()).isEqualTo("Sandwich");
    }

    @Test
    void findAllItemsInNonExistentCartTest() {
        assertThrows(EntityNotFoundException.class, () -> cartService.findAllItems(2L));
    }

    @Test
    void addItemToCartTest() {
        cartService.addItem(1L, 3L);
        Cart actual = cartRepository.findById(1L).orElseThrow(EntityNotFoundException::new);

        assertThat(actual.getItems().size()).isEqualTo(3);
        assertThat(actual.getItems().get(2).getName()).isEqualTo("Pizza");
    }

    @Test
    void deleteItemTest() {
        assertDoesNotThrow(() -> cartService.removeItem(1L, 1L));
    }

    @Test
    void deleteItemNotInCartTest() {
        assertThrows(EntityNotFoundInListException.class, () -> cartService.removeItem(1L, 3L));
    }
}
