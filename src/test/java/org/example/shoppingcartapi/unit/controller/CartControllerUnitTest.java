package org.example.shoppingcartapi.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.shoppingcartapi.contract.request.AddItemRequest;
import org.example.shoppingcartapi.controller.CartController;
import org.example.shoppingcartapi.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CartService cartService;

    @Test
    void allItemsInCartTest() throws Exception {
        this.mockMvc.perform(get("/cart/{cartId}/items", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void addItemToCartTest() throws Exception {
        this.mockMvc.perform(post("/cart/{cartId}/items", 1L)
                        .content(objectMapper.writeValueAsString(new AddItemRequest(1L)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteItemTest() throws Exception {
        this.mockMvc.perform(delete("/cart/{cartId}/items/{itemId}", 1L, 1L))
                .andExpect(status().isOk());
    }

    @Test
    void addItemToCartFailTest() throws Exception {
        this.mockMvc.perform(post("/cart/{cartId}/items", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
