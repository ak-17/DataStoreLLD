package com.github.akshay.datastore.service;

import com.github.akshay.datastore.exceptions.InvalidTypeException;
import com.github.akshay.datastore.handler.ListTypeHandler;

import java.util.Collection;
import java.util.List;

public class ListStoreHandler implements IListStoreService {
    private final ListTypeHandler listTypeHandler;

    public ListStoreHandler() {
        listTypeHandler = new ListTypeHandler();
    }

    public void lAdd(String key, Object value, Object... values) {
        try {
            listTypeHandler.lAdd(key, value, values);
        } catch (InvalidTypeException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("successfully added list. key: " + key);
    }

    public List<Object> lGet(String key, int index, int... indexes) {
        List<Object> objectList = listTypeHandler.lGet(key, index, indexes);
        StringBuilder sb = new StringBuilder();
        sb.append("LGET:" + key + " , values: ");

        for (Object obj : objectList) {
            sb.append(obj + " ");
        }
        System.out.println(sb.toString());
        return objectList;
    }

    public Collection<Object> lGetAll(String key) {
        Collection<Object> objectList = listTypeHandler.getAll(key);
        if (objectList == null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append("LGETAll:" + key + " , values: ");

        for (Object obj : objectList) {
            sb.append(obj + " ");
        }
        System.out.println(sb.toString());
        return objectList;
    }

    public void lDelete(String key, int index, int... indexes) {
        listTypeHandler.lDel(key, index, indexes);
    }

}
