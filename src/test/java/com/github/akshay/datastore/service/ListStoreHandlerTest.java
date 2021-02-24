package com.github.akshay.datastore.service;

import com.github.akshay.datastore.handler.ListTypeHandler;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListStoreHandlerTest {

    @Test
    public void listStoreHandlerTest() {
        ListStoreHandler listStoreHandler = new ListStoreHandler();

        listStoreHandler.lAdd("key1", 3,4,5,3);
        List<Object> list = (List)listStoreHandler.lGetAll("key1");
        assertEquals(4, list.size());

        listStoreHandler.lAdd("key1", 100,101,102);
        list = (List)listStoreHandler.lGetAll("key1");
        assertEquals(7, list.size());

        list= listStoreHandler.lGet("key1", 0,1);
        assertTrue(list!=null && list.size()==2);
        assertTrue(list.get(0).equals(3) && list.get(1).equals(4));

        list =(List) listStoreHandler.lGetAll("key2");
        assertNull(list);

    }

}