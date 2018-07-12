package com.demo;

import com.message.OperationMessage;

/**
 * A Java Bean represents an operation message.
 */
public class Operation implements OperationMessage {
    public String productType;
    public double value;
    public Operation operation;

    public Operation(String productType, double value, Operation operation) {
        this.productType = productType;
        this.value = value;
        this.operation = operation;
    }

    @Override
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
