package com.github.akshay.datastore.model;

import java.util.Collection;

public abstract class CollectionEntry extends Entry {

    private Collection<PrimitiveEntry> primitiveEntryList;

    CollectionEntry(Collection<PrimitiveEntry> list) {
        this.primitiveEntryList = list;
    }

    public Collection<PrimitiveEntry> getPrimitiveEntryList() {
        return primitiveEntryList;
    }

    public void setPrimitiveEntryList(Collection<PrimitiveEntry> primitiveEntryList) {
        this.primitiveEntryList = primitiveEntryList;
    }
}
