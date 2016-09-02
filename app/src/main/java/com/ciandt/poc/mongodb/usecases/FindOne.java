package com.ciandt.poc.mongodb.usecases;

import com.ciandt.poc.mongodb.domains.Cart;
import com.ciandt.poc.mongodb.gateways.CartDBGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rodrigosd on 02/09/16.
 */
@Service
@Slf4j
public class FindOne {

    private CartDBGateway cartDBGateway;

    @Autowired
    public FindOne(final CartDBGateway cartDBGateway) {
        this.cartDBGateway = cartDBGateway;
    }

    public Cart execute(String id) {
        log.info("executing");
        return cartDBGateway.findOne(id);
    }
}
