package com.github.akshay.datastore.handler;

import com.github.akshay.datastore.common.Utils;
import com.github.akshay.datastore.exceptions.InvalidOperationException;
import com.github.akshay.datastore.exceptions.InvalidTypeException;
import com.github.akshay.datastore.model.CollectionEntry;
import com.github.akshay.datastore.model.enums.PrimitiveTypes;
import com.github.akshay.datastore.repository.DataStoreRepo;

import java.util.Collection;

public abstract class CollectionTypeHandler extends PrimitiveTypeHandler {

    @Override
    public Object get(String key) {
        throw new InvalidOperationException();
    }

    @Override
    public void set(String key, Object value) {
        throw new InvalidOperationException();
    }

    @Override
    public void del(String key) {
        DataStoreRepo.primitiveTypeMap.remove(key);
        DataStoreRepo.primitiveEntryMap.remove(key);
        DataStoreRepo.collectionEntryMap.remove(key);
        DataStoreRepo.collectionTypeMap.remove(key);
    }

    public Collection getAll(String key) {

        CollectionEntry collectionEntry = DataStoreRepo.collectionEntryMap.get(key);
        if (collectionEntry == null) {
            return null;
        }

        return collectionEntry.getPrimitiveEntryList();
    }

    protected void addAll(String key, Object value, Object... values) {

        CollectionEntry collectionEntry = DataStoreRepo.collectionEntryMap.get(key);
        PrimitiveTypes reqType = DataStoreRepo.primitiveTypeMap.get(key);
        int n = values.length;
        Object[] objectList = new Object[n + 1];
        objectList[0] = value;
        for (int i = 1; i <= n; i++) {
            objectList[i] = values[i - 1];
        }

        Collection hashSet = collectionEntry.getPrimitiveEntryList();

        for (Object obj : objectList) {
            PrimitiveTypes gotType = Utils.getPrimitiveType(obj);
            if (reqType != gotType) {
                throw new InvalidTypeException(Utils.getInvalidTypeExceptionString(reqType, gotType));
            }
            hashSet.add(obj);
        }
        return;
    }

}
