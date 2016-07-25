package com.hashirbaig.creator.shopkeeper.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CustomersData {

    private static CustomersData sCustomersData;

    private List<Customer> mCustomerList;

    private CustomersData(Context context) {
        mCustomerList = new ArrayList<>();
    }

    public static CustomersData get(Context context) {
        if(sCustomersData == null) {
            sCustomersData = new CustomersData(context);
        }
        return sCustomersData;
    }

    public List<Customer> getCustomerList() {
        return mCustomerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        mCustomerList = customerList;
    }

    public void add(Customer customer) {
        mCustomerList.add(customer);
    }
}
