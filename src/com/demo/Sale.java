package com.demo;

import com.message.SaleMessage;

/**
 * A Java Bean represents a sale message.
 */
public class Sale implements SaleMessage {
    private String productType;
    private double value;
    private int number;

    public Sale(String productType, double value) {
        this.productType = productType;
        this.value = value;
    }

    public Sale(String productType, double value, int number) {
        this.productType = productType;
        this.value = value;
        this.number = number;
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

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
