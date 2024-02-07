package org.example.shoppingcartapi.repository;

import org.example.shoppingcartapi.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Daniél Garrido
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
