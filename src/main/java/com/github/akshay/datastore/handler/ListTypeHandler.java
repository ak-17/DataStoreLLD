package com.github.akshay.datastore.handler;

import com.github.akshay.datastore.common.Utils;
import com.github.akshay.datastore.exceptions.InvalidTypeException;
import com.github.akshay.datastore.model.ArrayListEntry;
import com.github.akshay.datastore.model.CollectionEntry;
import com.github.akshay.datastore.model.enums.CollectionType;
import com.github.akshay.datastore.model.enums.PrimitiveTypes;
import com.github.akshay.datastore.repository.DataStoreRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTypeHandler extends CollectionTypeHandler {

    public void lAdd(String key, Object value, Object... values) {
        validateCollectionType(key,CollectionType.ARRAYLIST);
        CollectionEntry collectionEntry = DataStoreRepo.collectionEntryMap.get(key);
        PrimitiveTypes reqType = DataStoreRepo.primitiveTypeMap.get(key);
        CollectionType collectionType = getCollectionType(key);
        if (collectionEntry == null || reqType == null || collectionType == null) {
            del(key);
            addNewList(key, value, values);
            return;
        }

        addAll(key, value, values);
        return;
    }

    public List<Object> lGet(String key, int index, int... indexes) {
        validateCollectionType(key,CollectionType.ARRAYLIST);
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

    public void lDel(String key, int index, int... indexes) {
        validateCollectionType(key,CollectionType.ARRAYLIST);
        List<Object> objectList = new ArrayList<>();
        int n = indexes.length;
        int[] indexList = new int[n + 1];
        indexList[0] = index;
        for (int i = 1; i <= n; i++) {
            indexList[i] = indexes[i - 1];
        }
        CollectionEntry collectionEntry = DataStoreRepo.collectionEntryMap.get(key);
        if (collectionEntry == null) {
            return;
        }
        ArrayList arrayList = (ArrayList) collectionEntry.getPrimitiveEntryList();

        int size = arrayList.size();

        Arrays.sort(indexList);


        for (int i = indexList.length - 1; i >= 0; i--) {
            if (indexList[i] >= size) continue;
            arrayList.remove(indexList[i]);
        }

        return;
    }

    private void addNewList(String key, Object value, Object... values) {
        int n = values.length;
        Object[] objectList = new Object[n + 1];
        objectList[0] = value;
        for (int i = 1; i <= n; i++) {
            objectList[i] = values[i - 1];
        }

        PrimitiveTypes reqType = Utils.getPrimitiveType(value);


        ArrayList arrayList = new ArrayList();

        for (Object obj : objectList) {
            PrimitiveTypes gotType = Utils.getPrimitiveType(obj);
            if (reqType != gotType) {
                throw new InvalidTypeException(Utils.getInvalidTypeExceptionString(reqType, gotType));
            }
        }


        for (Object obj : objectList) {
            arrayList.add(obj);
        }
        DataStoreRepo.collectionTypeMap.put(key, CollectionType.ARRAYLIST);
        DataStoreRepo.primitiveTypeMap.put(key, reqType);
        DataStoreRepo.collectionEntryMap.put(key, new ArrayListEntry(arrayList));

        return;
    }


}
