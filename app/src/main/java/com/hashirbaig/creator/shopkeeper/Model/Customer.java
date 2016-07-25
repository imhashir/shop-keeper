package com.hashirbaig.creator.shopkeeper.Model;

import java.util.Date;

public class Customer {

    private String mName;
    private double mPurchasePrice;
    private String mNumber;
    private String mProduct;
    private Date mDate;

    public Customer() {
        mDate = new Date();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getProduct() {
        return mProduct;
    }

    public void setProduct(String product) {
        mProduct = product;
    }

    public double getPurchasePrice() {
        return mPurchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        mPurchasePrice = purchasePrice;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isOK() {
        if (mName != null && mProduct != null) {
            return true;
        } else {
            return false;
        }
    }
}
