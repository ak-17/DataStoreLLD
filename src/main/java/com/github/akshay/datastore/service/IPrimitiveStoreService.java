package com.github.akshay.datastore.service;

import java.util.Collection;

interface IPrimitiveStoreService {

    Object get(String key);

    void set(String key, Object value);

    void delete(String key);

}

interface ISetStoreService {
    void sAdd(String key, Object value, Object... values);

    Collection<Object> sGetAll(String key);

    Collection<Boolean> sDel(String key, Object value, Object... values);

    Collection<Boolean> sContains(String key, Object value, Object... values);
}

interface IListStoreService {
    Collection<Object> lGet(String key, int index, int... indexes);

    Collection<Object> lGetAll(String key);

    void lAdd(String key, Object value, Object... values);

    void lDelete(String key, int index, int... indexes);
}
