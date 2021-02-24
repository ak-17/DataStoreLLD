package com.github.akshay.datastore.service;


import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetDataStoreServiceTest {

    @Test
    public void setDataStoreServiceTest() {
        SetDataStoreService setDataStoreService = new SetDataStoreService();

        setDataStoreService.sAdd("key1", 3,4,5,5,5);
        Collection collection = setDataStoreService.sGetAll("key1");
        assertEquals(3, collection.size());

        setDataStoreService.sDel("key1", 5);
        collection = setDataStoreService.sGetAll("key1");
        assertEquals(2, collection.size());

        collection = setDataStoreService.sContains("key1", 3, 5);
        assertEquals(2, collection.size());
        List<Object> list = (List) collection;
        assertEquals(true, list.get(0));
        assertEquals(false, list.get(1));
    }

}