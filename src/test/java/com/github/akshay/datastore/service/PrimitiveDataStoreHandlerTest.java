package com.github.akshay.datastore.service;

import com.github.akshay.datastore.handler.PrimitiveTypeHandler;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PrimitiveDataStoreHandlerTest {

    @Test
    public void primitiveDataStoreTest() {
        PrimitiveDataStoreHandler primitiveDataStoreHandler = new PrimitiveDataStoreHandler();

        primitiveDataStoreHandler.set("key1", 1);
        Object actualValue = primitiveDataStoreHandler.get("key1");
        assertEquals(1,actualValue);

        primitiveDataStoreHandler.set("key1", 5);
        actualValue = primitiveDataStoreHandler.get("key1");
        assertEquals(5,actualValue);

        primitiveDataStoreHandler.set("key1", "akshay");
        actualValue = primitiveDataStoreHandler.get("key1");
        assertEquals(5,actualValue);

        primitiveDataStoreHandler.delete("key1");
        actualValue = primitiveDataStoreHandler.get("key1");
        assertNull(actualValue);

        primitiveDataStoreHandler.set("key1", "value1");
        actualValue = primitiveDataStoreHandler.get("key1");
        assertEquals("value1",actualValue);
    }

}