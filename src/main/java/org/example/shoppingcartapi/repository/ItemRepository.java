package org.example.shoppingcartapi.repository;

import org.example.shoppingcartapi.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Daniél Garrido
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
