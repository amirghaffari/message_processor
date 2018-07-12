package com.message;

/**
 * provides methods which are needed by the Message Processor to access and update an operation message
 */
public interface OperationMessage {
    enum Operation {ADD, SUBTRACT, MULTIPLY};
    String getProductType();
    double getValue();
    Operation getOperation();
}
