package com.github.akshay.datastore.model;

public class PrimitiveEntry<T> extends Entry {

    private T value;

    public PrimitiveEntry(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
