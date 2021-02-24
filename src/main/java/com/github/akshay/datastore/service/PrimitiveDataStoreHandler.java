package com.github.akshay.datastore.service;

import com.github.akshay.datastore.exceptions.InvalidTypeException;
import com.github.akshay.datastore.handler.PrimitiveTypeHandler;

public class PrimitiveDataStoreHandler implements IPrimitiveStoreService {

    private PrimitiveTypeHandler primitiveTypeHandler;

    public PrimitiveDataStoreHandler() {
        this.primitiveTypeHandler = new PrimitiveTypeHandler();
    }

    public Object get(String key) {
        Object value = primitiveTypeHandler.get(key);
        return value;
    }

    public void set(String key, Object value) {
        try {
            primitiveTypeHandler.set(key, value);
        } catch (InvalidTypeException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("successfully added. key: " + key + " value:" + value);
    }

    public void delete(String key) {
        primitiveTypeHandler.del(key);
        System.out.println("successfully deleted. key: " + key);
    }


}
