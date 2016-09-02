package com.ciandt.poc.mongodb.gateways.http;

import com.ciandt.poc.mongodb.usecases.AddItem;
import com.ciandt.poc.mongodb.usecases.FindOne;
import com.ciandt.poc.mongodb.domains.Cart;
import com.ciandt.poc.mongodb.usecases.FindAll;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by guilherme.roveri on 4/24/16.
 */
@RestController
@RequestMapping("/api/sync")
@Slf4j
@Api(value = "/api/sync", description = "Rest api for do operation on cart", produces = MediaType.APPLICATION_JSON_VALUE)
public class SyncCartController {

    private static final String STORE_ID_HEADER = "storeId";
    private static final String CAMPAIGN_HEADER = "campaign";
    private static final String UUID_HEADER = "uuid";

    private final AddItem addItem;

    private final FindAll findAll;

    private final FindOne findOne;

    @Autowired
    public SyncCartController(final AddItem addItem, final FindAll findAll, final FindOne findOne) {
        this.addItem = addItem;
        this.findAll = findAll;
        this.findOne = findOne;
    }

    @ApiOperation(value = "Add a new cart")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Item added", response = Cart.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Items not found"),
            @ApiResponse(code = 405, message = "Method Not Allowed"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @RequestMapping(value = "/carts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSync(@RequestBody final Cart cart) {
        addItem.execute(cart);
    }

    @ApiOperation(value = "Get all carts")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Item added", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Items not found"),
            @ApiResponse(code = 405, message = "Method Not Allowed"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @RequestMapping(value = "/carts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Cart> findAllSync() {
        return findAll.execute();
    }



    @ApiOperation(value = "Get cart by id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Item added", response = Cart.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Items not found"),
            @ApiResponse(code = 405, message = "Method Not Allowed"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @RequestMapping(value = "/carts/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Cart findOneSync(@PathVariable("id") String id) {
        return findOne.execute(id);
    }
}

