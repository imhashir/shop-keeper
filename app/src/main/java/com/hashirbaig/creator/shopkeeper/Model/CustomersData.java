package com.hashirbaig.creator.shopkeeper.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.INotificationSideChannel;

import com.hashirbaig.creator.shopkeeper.Database.CursorWrapperHelper;
import com.hashirbaig.creator.shopkeeper.Database.DatabaseSchema;
import com.hashirbaig.creator.shopkeeper.Database.DatabaseSchema.CustomerTable;
import com.hashirbaig.creator.shopkeeper.Database.ShopDBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomersData {

    private static CustomersData sCustomersData;

    private SQLiteDatabase mDatabase;
    private Context mContext;
    private List<Customer> mCustomerList;

    private CustomersData(Context context) {
        mCustomerList = new ArrayList<>();
        mContext = context.getApplicationContext();
        mDatabase = new ShopDBHelper(mContext).getWritableDatabase();
        readDatabase();
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

    private void readDatabase() {
        Cursor cursor = mDatabase.query(
                CustomerTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        CursorWrapperHelper helper = new CursorWrapperHelper(cursor);

        if(helper.getCount() > 0) {
            try {
                helper.moveToFirst();
                while (!helper.isAfterLast()) {
                    mCustomerList.add(helper.getCustomer());
                    helper.moveToNext();
                }
            } finally {
                helper.close();
            }
        }
    }

    public void add(Customer customer) {
        mCustomerList.add(customer);
        mDatabase.insert(CustomerTable.TABLE_NAME, null, getContentValues(customer));
    }

    public Customer get(int position) {
        return mCustomerList.get(position);
    }

    public ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();
        values.put(CustomerTable.cols.NAME, customer.getName());
        values.put(CustomerTable.cols.DATE, customer.getDate().getTime());
        values.put(CustomerTable.cols.UUID, customer.getUUID().toString());
        values.put(CustomerTable.cols.NUMBER, customer.getNumber());
        values.put(CustomerTable.cols.PRODUCT, customer.getProduct());
        values.put(CustomerTable.cols.PRICE, customer.getPurchasePrice());

        return values;
    }

    public Customer find(UUID id) {
        for(Customer customer : mCustomerList) {
            if(customer.getUUID().equals(id)) {
                return customer;
            }
        }
        return null;
    }
}
