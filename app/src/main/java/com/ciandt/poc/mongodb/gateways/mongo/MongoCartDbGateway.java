package com.ciandt.poc.mongodb.gateways.mongo;

import com.ciandt.poc.mongodb.gateways.CartDBGateway;
import com.ciandt.poc.mongodb.domains.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rodrigosd on 01/09/16.
 */
@Component
public class MongoCartDbGateway implements CartDBGateway {


    private CartRepository repository;

    @Autowired
    public MongoCartDbGateway(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cart save(Cart cart) {
        return repository.save(cart);
    }

    @Override
    public List<Cart> findAll() {
        return repository.findAll();
    }

    @Override
    public Cart findOne(String id) {
        return repository.findOne(id);
    }
}
