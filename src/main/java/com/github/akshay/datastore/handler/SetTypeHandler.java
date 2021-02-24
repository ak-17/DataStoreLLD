package com.github.akshay.datastore.handler;

import com.github.akshay.datastore.common.Utils;
import com.github.akshay.datastore.exceptions.InvalidOperationException;
import com.github.akshay.datastore.exceptions.InvalidTypeException;
import com.github.akshay.datastore.model.CollectionEntry;
import com.github.akshay.datastore.model.HashSetEntry;
import com.github.akshay.datastore.model.enums.CollectionType;
import com.github.akshay.datastore.model.enums.PrimitiveTypes;
import com.github.akshay.datastore.repository.DataStoreRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SetTypeHandler extends CollectionTypeHandler {

    public void sAdd(String key, Object value, Object... values) {
        validateCollectionType(key,CollectionType.HASHSET);
        CollectionEntry collectionEntry = DataStoreRepo.collectionEntryMap.get(key);
        CollectionType collectionType = getCollectionType(key);
        PrimitiveTypes reqType = DataStoreRepo.primitiveTypeMap.get(key);
        if (collectionEntry == null || reqType == null || collectionType == null) {
            del(key);
            addNewSet(key, value, values);
            return;
        }
        if (collectionType != CollectionType.HASHSET) {
            throw new InvalidOperationException();
        }

        addAll(key, value, values);
        return;
    }

    public List<Object> lGet(String key, int index, int... indexes) {
        validateCollectionType(key,CollectionType.HASHSET);
        List<Object> objectList = new ArrayList<>();
        int n = indexes.length;
        int[] indexList = new int[n + 1];
        indexList[0] = index;
        System.arraycopy(indexes, 0, indexList, 1, n);
        CollectionEntry collectionEntry = DataStoreRepo.collectionEntryMap.get(key);
        if (collectionEntry == null) {
            return null;
        }
        ArrayList arrayList = (ArrayList) collectionEntry.getPrimitiveEntryList();

        int size = arrayList.size();

        for (int i : indexList) {
            if (i >= size) continue;
            objectList.add(arrayList.get(i));
        }

        return objectList;
    }

    public List<Boolean> sDel(String key, Object value, Object... values) {
        validateCollectionType(key,CollectionType.HASHSET);
        List<Boolean> booleans = new ArrayList<>();

        CollectionEntry collectionEntry = DataStoreRepo.collectionEntryMap.get(key);
        PrimitiveTypes reqType = DataStoreRepo.primitiveTypeMap.get(key);
        if (collectionEntry == null) {
            return null;
        }
        int n = values.length;
        Object[] objectList = new Object[n + 1];
        objectList[0] = value;
        for (int i = 1; i <= n; i++) {
            objectList[i] = values[i - 1];
        }

        HashSet hashSet = (HashSet) collectionEntry.getPrimitiveEntryList();

        for (Object obj : objectList) {

            booleans.add(hashSet.remove(obj));

        }
        return booleans;
    }

    public List<Boolean> sContains(String key, Object value, Object... values) {
        validateCollectionType(key,CollectionType.HASHSET);
        List<Boolean> booleans = new ArrayList<>();

        CollectionEntry collectionEntry = DataStoreRepo.collectionEntryMap.get(key);
        PrimitiveTypes reqType = DataStoreRepo.primitiveTypeMap.get(key);
        if (collectionEntry == null) {
            return null;
        }
        int n = values.length;
        Object[] objectList = new Object[n + 1];
        objectList[0] = value;
        for (int i = 1; i <= n; i++) {
            objectList[i] = values[i - 1];
        }

        HashSet hashSet = (HashSet) collectionEntry.getPrimitiveEntryList();

        for (Object obj : objectList) {

            booleans.add(hashSet.contains(obj));

        }
        return booleans;
    }

    private void addNewSet(String key, Object value, Object... values) {
        int n = values.length;
        Object[] objectList = new Object[n + 1];
        objectList[0] = value;
        for (int i = 1; i <= n; i++) {
            objectList[i] = values[i - 1];
        }

        PrimitiveTypes reqType = Utils.getPrimitiveType(value);


        HashSet hashSet = new HashSet<>();

        for (Object obj : objectList) {
            PrimitiveTypes gotType = Utils.getPrimitiveType(obj);
            if (reqType != gotType) {
                throw new InvalidTypeException(Utils.getInvalidTypeExceptionString(reqType, gotType));
            }
        }


        for (Object obj : objectList) {
            hashSet.add(obj);
        }
        DataStoreRepo.collectionTypeMap.put(key, CollectionType.HASHSET);
        DataStoreRepo.primitiveTypeMap.put(key, reqType);
        DataStoreRepo.collectionEntryMap.put(key, new HashSetEntry(hashSet));

        return;
    }

}
