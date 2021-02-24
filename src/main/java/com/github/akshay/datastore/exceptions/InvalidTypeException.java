package com.github.akshay.datastore.exceptions;

public class InvalidTypeException extends RuntimeException {

    public InvalidTypeException(String message) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Invalid type exception: ")
//                .append("required type: ")
//                .append(reqType)
//                .append(" got type: ")
//                .append(gotType);

        super(message);
    }
}
