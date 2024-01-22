package org.example.shoppingcartapi;

import org.example.shoppingcartapi.entity.Cart;
import org.example.shoppingcartapi.entity.Item;
import org.example.shoppingcartapi.repository.CartRepository;
import org.example.shoppingcartapi.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniél Garrido
 */
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initializeDatabase(CartRepository cartRepository, ItemRepository itemRepository) {
        return args -> {
            itemRepository.save(new Item("Banana"));
            itemRepository.save(new Item("Oat milk"));

            Optional<Item> banana = itemRepository.findById(2L);
            banana.ifPresent(item -> cartRepository.save(new Cart(List.of(item))));
        };
    }
}
