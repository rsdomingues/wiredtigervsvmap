package com.ciandt.poc.mongodb.gateways;

import com.ciandt.poc.mongodb.domains.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by rodrigosd on 4/25/16.
 */
public class CartDBGatewayInMemory implements CartDBGateway {

    private static Map<String, Cart> carts = new HashMap<>();

    @Override
    public Cart save(final Cart cart) {
        if (cart.getId() == null) {
            cart.setId(UUID.randomUUID().toString());
        }
        String id = cart.getId();
        carts.put(id, cart);

        return cart;
    }

    @Override
    public List<Cart> findAll() {
        return new ArrayList<>(carts.values());
    }

    public Cart findOne(final String id) {
        return carts.get(id);
    }




}
