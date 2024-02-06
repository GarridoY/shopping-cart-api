package org.example.shoppingcartapi.configuration;

import org.example.shoppingcartapi.domain.Cart;
import org.example.shoppingcartapi.domain.Item;
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
    public CommandLineRunner initializeDatabase(CartRepository cartRepository, ItemRepository itemRepository) {
        return args -> {
            itemRepository.save(new Item("Banana"));
            itemRepository.save(new Item("Oat milk"));

            Optional<Item> banana = itemRepository.findById(2L);
            banana.ifPresent(item -> cartRepository.save(new Cart(List.of(item))));
        };
    }
}
