package com.hashirbaig.creator.shopkeeper.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hashirbaig.creator.shopkeeper.Database.CursorWrapperHelper;
import com.hashirbaig.creator.shopkeeper.Database.DatabaseSchema.TableCustomers;
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
                TableCustomers.TABLE_CUSTOMERS,
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
        mDatabase.insert(TableCustomers.TABLE_CUSTOMERS, null, getContentValues(customer));
    }

    public void update(Customer customer) {
        mDatabase.update(TableCustomers.TABLE_CUSTOMERS, getContentValues(customer), TableCustomers.cols.UUID + " = ?", new String[]{customer.getUUID().toString()});
    }

    public void delete(Customer customer) {
        mDatabase.delete(TableCustomers.TABLE_CUSTOMERS, TableCustomers.cols.UUID + " = ?", new String[]{customer.getUUID().toString()});
        for(int x = 0; x < mCustomerList.size(); x++) {
            if(mCustomerList.get(x).getUUID().equals(customer.getUUID())) {
                mCustomerList.remove(x);
                return;
            }
        }
    }

    public Customer get(int position) {
        return mCustomerList.get(position);
    }

    public ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();
        values.put(TableCustomers.cols.NAME, customer.getName());
        values.put(TableCustomers.cols.DATE, customer.getDate().getTime());
        values.put(TableCustomers.cols.UUID, customer.getUUID().toString());
        values.put(TableCustomers.cols.NUMBER, customer.getNumber());
        values.put(TableCustomers.cols.PRODUCT, customer.getProduct());
        values.put(TableCustomers.cols.PRICE, customer.getPurchasePrice());

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
