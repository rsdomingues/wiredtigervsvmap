package com.ciandt.poc.mongodb.gateways.http;

import com.ciandt.poc.mongodb.gateways.http.async.DeferredResultAdapter;
import com.ciandt.poc.mongodb.usecases.AddItem;
import com.ciandt.poc.mongodb.usecases.FindAll;
import com.ciandt.poc.mongodb.usecases.FindOne;
import com.ciandt.poc.mongodb.domains.Cart;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

/**
 * Created by rodrigosd on 7/18/16.
 */
@RestController
@RequestMapping("/api/async")
@Slf4j
@Api(value = "/api/async", description = "Rest api for demo async (slowly) operation", produces = MediaType.APPLICATION_JSON_VALUE)
public class AsyncCartController {

    private final TaskExecutor executor;
    private final AddItem addItem;
    private final FindAll findAll;
    private final FindOne findOne;

    @Autowired
    public AsyncCartController(final TaskExecutor executor, final AddItem addItem, final FindAll findAll, final FindOne findOne) {
        this.executor = executor;
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
    public DeferredResult<Cart> saveAsync(@RequestBody Cart cart) {
        return DeferredResultAdapter.requestAsync(executor, () -> addItem.execute(cart));
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
    public DeferredResult<List<Cart>> findAllAsync() {
        return DeferredResultAdapter.requestAsync(executor, () -> findAll.execute());
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
    public DeferredResult<Cart> findOneAsync(@PathVariable("id") String id) {
        return DeferredResultAdapter.requestAsync(executor, () -> findOne.execute(id));
    }

}
