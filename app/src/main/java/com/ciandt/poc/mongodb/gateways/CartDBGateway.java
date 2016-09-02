package com.ciandt.poc.mongodb.gateways;

import com.ciandt.poc.mongodb.domains.Cart;

import java.util.List;

/**
 * Created by rodrigosd on 4/25/16.
 */
public interface CartDBGateway {

    Cart save(Cart cart);

    List<Cart> findAll();

    Cart findOne(String id);

}
