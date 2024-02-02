package org.example.shoppingcartapi.mapper;

import org.example.shoppingcartapi.entity.Item;
import org.example.shoppingcartapi.response.ItemResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Daniél Garrido
 */
@Service
public class ItemMapper implements Function<Item, ItemResponse> {

    @Override
    public ItemResponse apply(Item item) {
        return new ItemResponse(item.getName());
    }
}
