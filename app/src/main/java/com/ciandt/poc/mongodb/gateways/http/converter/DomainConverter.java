package com.ciandt.poc.mongodb.gateways.http.converter;

/**
 * Created by rodrigosd on 4/27/16.
 */
@FunctionalInterface
public interface DomainConverter<T, F> {

    T convert(F f);

}
