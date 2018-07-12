package com.message;

/**
 * provides methods which are needed by the Message Processor to access and update a sale message
 */
public interface SaleMessage {
    String getProductType();
    double getValue();
    void setValue(double value);
    int getNumber();
}
