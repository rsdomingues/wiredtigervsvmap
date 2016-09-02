package com.ciandt.poc.mongodb.usecases;

import com.ciandt.poc.mongodb.domains.Cart;
import com.ciandt.poc.mongodb.gateways.CartDBGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rodrigosd on 01/09/16.
 */
@Service
@Slf4j
public class FindAll {

    private CartDBGateway cartDBGateway;

    @Autowired
    public FindAll(final CartDBGateway cartDBGateway) {
        this.cartDBGateway = cartDBGateway;
    }

    public List<Cart> execute() {
        log.info("executing");
        return cartDBGateway.findAll();
    }
}
