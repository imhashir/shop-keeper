package com.hashirbaig.creator.shopkeeper.Model;

import java.util.UUID;

public class Product {

    private String mName;
    private Integer mAmountInStock;
    private Double mPrice;
    private String mSerialNumber;
    private UUID mUUID;

    public Product() {
        mUUID = UUID.randomUUID();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Integer getAmountInStock() {
        return mAmountInStock;
    }

    public void setAmountInStock(Integer amountInStock) {
        mAmountInStock = amountInStock;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        mPrice = price;
    }

    public String getSerialNumber() {
        return mSerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        mSerialNumber = serialNumber;
    }

    public boolean isOK() {
        if((mName == null && mSerialNumber == null) || mPrice == null) {
            return false;
        }
        return true;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }
}
