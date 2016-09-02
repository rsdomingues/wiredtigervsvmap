package com.ciandt.poc.mongodb.gateways.mongo;

import com.ciandt.poc.mongodb.domains.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rodrigosd on 01/09/16.
 */
@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
}
