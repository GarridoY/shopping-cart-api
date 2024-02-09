package org.example.shoppingcartapi.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.shoppingcartapi.contract.request.AddItemRequest;
import org.example.shoppingcartapi.domain.Cart;
import org.example.shoppingcartapi.domain.Item;
import org.example.shoppingcartapi.repository.CartRepository;
import org.example.shoppingcartapi.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        Item item = new Item("Tofu");
        itemRepository.save(item);

        Cart cart = new Cart(new ArrayList<>());
        cartRepository.save(cart);
    }

    @Test
    @Transactional
    void addItemToCartIntegrationTest() throws Exception {
        // Add Item[ID: 1] to Cart[ID: 1]
        this.mockMvc.perform(post("/cart/{cartId}/items", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new AddItemRequest(1L))))
                .andExpect(status().isOk());

        Cart cart = cartRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        List<Item> items = cart.getItems();

        assertThat(items.size()).isEqualTo(1);
        assertThat(items.get(0).getName()).isEqualTo("Tofu");
    }
}
