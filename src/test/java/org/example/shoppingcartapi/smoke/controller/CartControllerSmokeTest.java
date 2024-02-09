package org.example.shoppingcartapi.smoke.controller;

import org.example.shoppingcartapi.controller.CartController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CartControllerSmokeTest {

    @Autowired
    private CartController controller;

    @Test
    void cartControllerLoads() {
        assertThat(controller).isNotNull();
    }
}
