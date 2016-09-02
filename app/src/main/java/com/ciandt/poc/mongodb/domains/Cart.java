package com.ciandt.poc.mongodb.domains;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Cart implements Serializable {

    private static final long serialVersionUID = 6593302125147043168L;

    public enum CartStatus {OPENED, ACCOMPLISHED, ABANDONED}

    @NotNull(message = "cart.cartId.nullable")
    private String id;
    private String campaign;
    private CartStatus status = CartStatus.OPENED;
}
