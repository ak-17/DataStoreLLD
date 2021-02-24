package com.github.akshay.datastore.service;

import com.github.akshay.datastore.handler.SetTypeHandler;

import java.util.Collection;

public class SetDataStoreService implements ISetStoreService {

    private SetTypeHandler setTypeHandler;

    public SetDataStoreService() {
        this.setTypeHandler = new SetTypeHandler();
    }

    public void sAdd(String key, Object value, Object... values) {
        try {
            setTypeHandler.sAdd(key, value, values);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Collection<Object> sGetAll(String key) {
        Collection<Object> objectList = setTypeHandler.getAll(key);
        if (objectList == null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append("SGETAll:" + key + " , values: ");

        for (Object obj : objectList) {
            sb.append(obj + " ");
        }
        System.out.println(sb.toString());
        return objectList;
    }

    public Collection<Boolean> sDel(String key, Object val, Object... vals) {
        Collection<Boolean> objectList = setTypeHandler.sDel(key, val, vals);
        if (objectList == null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append("SDelAll:" + key + " , values: ");

        for (Object obj : objectList) {
            sb.append(obj + " ");
        }
        System.out.println(sb.toString());
        return objectList;
    }

    @Override
    public Collection<Boolean> sContains(String key, Object value, Object... values) {
        Collection<Boolean> objectList = setTypeHandler.sContains(key, value, values);
        if (objectList == null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append("SContains:" + key + " , values: ");

        for (Object obj : objectList) {
            sb.append(obj + " ");
        }
        System.out.println(sb.toString());
        return objectList;
    }
}
