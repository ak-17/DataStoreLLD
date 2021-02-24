package com.github.akshay.datastore.repository;

import com.github.akshay.datastore.model.CollectionEntry;
import com.github.akshay.datastore.model.PrimitiveEntry;
import com.github.akshay.datastore.model.enums.CollectionType;
import com.github.akshay.datastore.model.enums.PrimitiveTypes;

import java.util.HashMap;
import java.util.Map;

public class DataStoreRepo {

    // type map
    public static Map<String, PrimitiveTypes> primitiveTypeMap = new HashMap<>();
    public static Map<String, CollectionType> collectionTypeMap = new HashMap<>();


    // actual storage
    public static Map<String, PrimitiveEntry> primitiveEntryMap = new HashMap<>();
    public static Map<String, CollectionEntry> collectionEntryMap = new HashMap<>();

}
