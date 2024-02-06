package org.example.shoppingcartapi.domain;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author Daniél Garrido
 */
@Entity
public class Cart {

    private @Id @GeneratedValue Long id;
    @OneToMany
    private List<Item> items;

    public Cart() {
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
