package com.ciandt.poc.mongodb.usecases;

import com.ciandt.poc.mongodb.domains.Cart;
import com.ciandt.poc.mongodb.gateways.CartDBGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by guilherme.roveri on 4/19/16.
 */
@Service
@Slf4j
public class AddItem {

    private CartDBGateway cartDBGateway;

    @Autowired
    public AddItem(final CartDBGateway cartDBGateway) {
        this.cartDBGateway = cartDBGateway;
    }

    public Cart execute(final Cart cart) {
        log.info("execute with cardId = {} ", cart);

        cartDBGateway.save(cart);
        return cart;
    }

}
