package com.github.akshay.datastore.common;

import com.github.akshay.datastore.model.enums.PrimitiveTypes;

import java.util.ArrayList;
import java.util.HashSet;

public class Utils {
    public static PrimitiveTypes getPrimitiveType(Object value) {
        if (value == null) return PrimitiveTypes.UNDEFINED;

        if (Integer.class == value.getClass()) {
            return PrimitiveTypes.INTEGER;
        }

        if (String.class == value.getClass()) {
            return PrimitiveTypes.STRING;
        }

        if (Boolean.class == value.getClass()) {
            return PrimitiveTypes.BOOLEAN;
        }

        if (Long.class == value.getClass()) {
            return PrimitiveTypes.LONG;
        }

        if (Double.class == value.getClass()) {
            return PrimitiveTypes.DOUBLE;
        }

        if (Float.class == value.getClass()) {
            return PrimitiveTypes.FLOAT;
        }

        if (HashSet.class == value.getClass()) {
            return PrimitiveTypes.SET;
        }

        if (ArrayList.class == value.getClass()) {
            return PrimitiveTypes.LIST;
        }

        return PrimitiveTypes.UNDEFINED;
    }

    public static String getInvalidTypeExceptionString(PrimitiveTypes reqType, PrimitiveTypes gotType) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid type exception: ")
                .append("required type: ")
                .append(reqType)
                .append(" got type: ")
                .append(gotType);
        return sb.toString();
    }
}
