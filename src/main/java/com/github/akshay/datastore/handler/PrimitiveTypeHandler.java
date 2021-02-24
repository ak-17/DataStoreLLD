package com.github.akshay.datastore.handler;

import com.github.akshay.datastore.common.Utils;
import com.github.akshay.datastore.exceptions.InvalidOperationException;
import com.github.akshay.datastore.exceptions.InvalidTypeException;
import com.github.akshay.datastore.model.PrimitiveEntry;
import com.github.akshay.datastore.model.enums.CollectionType;
import com.github.akshay.datastore.model.enums.PrimitiveTypes;
import com.github.akshay.datastore.repository.DataStoreRepo;

public class PrimitiveTypeHandler {

    public Object get(String key) {
        validateCollectionType(key,CollectionType.PRIMITIVE);
        PrimitiveEntry primitiveEntry = DataStoreRepo.primitiveEntryMap.get(key);
        if (primitiveEntry == null) return null;
        return primitiveEntry.getValue();
    }

    public void set(String key, Object value) {
        validateCollectionType(key,CollectionType.PRIMITIVE);
        PrimitiveTypes reqType = getKeyPrimitiveType(key);
        if (reqType == null) {
            setNewKey(key, value);
            return;
        }
        PrimitiveTypes gotType = Utils.getPrimitiveType(value);
        if (reqType != gotType) {
            throw new InvalidTypeException(Utils.getInvalidTypeExceptionString(reqType, gotType));
        }
        DataStoreRepo.primitiveEntryMap.put(key, new PrimitiveEntry(value));
    }

    public void del(String key) {
        DataStoreRepo.primitiveTypeMap.remove(key);
        DataStoreRepo.primitiveEntryMap.remove(key);
        DataStoreRepo.collectionEntryMap.remove(key);
        DataStoreRepo.collectionTypeMap.remove(key);
    }

    private void setNewKey(String key, Object value) {
        DataStoreRepo.primitiveTypeMap.put(key, Utils.getPrimitiveType(value));
        DataStoreRepo.primitiveEntryMap.put(key, new PrimitiveEntry(value));
        DataStoreRepo.collectionTypeMap.put(key, CollectionType.PRIMITIVE);
    }

    private PrimitiveTypes getKeyPrimitiveType(String key) {
        return DataStoreRepo.primitiveTypeMap.get(key);
    }

    protected CollectionType getCollectionType(String key) {
        return DataStoreRepo.collectionTypeMap.get(key);
    }

    protected Boolean CollectionType(String key, CollectionType collectionType) {
        return collectionType.equals(DataStoreRepo.collectionTypeMap.get(key));
    }

    protected void validateCollectionType(String key, CollectionType collectionType) {
        CollectionType actualCollectionType = getCollectionType(key);
        if(actualCollectionType == null) return;
        if(!collectionType.equals(actualCollectionType)) {
            throw new InvalidOperationException("invalid operation on type :" + actualCollectionType);
        }
    }

}
