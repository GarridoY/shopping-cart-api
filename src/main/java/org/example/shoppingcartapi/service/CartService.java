package org.example.shoppingcartapi.service;

import org.example.shoppingcartapi.contract.response.ItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Daniél Garrido
 */
@Service
public interface CartService {

    List<ItemResponse> findAllItems(Long cartId);
    void addItem(Long cartId, Long itemId);
    void removeItem(Long cartId, Long itemId);
}
