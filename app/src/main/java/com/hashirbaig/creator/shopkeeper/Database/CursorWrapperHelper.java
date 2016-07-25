package com.hashirbaig.creator.shopkeeper.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.hashirbaig.creator.shopkeeper.Database.DatabaseSchema.CustomerTable;
import com.hashirbaig.creator.shopkeeper.Model.Customer;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hashir on 7/25/2016.
 */
public class CursorWrapperHelper extends CursorWrapper{

    public CursorWrapperHelper(Cursor cursor) {
        super(cursor);
    }

    public Customer getCustomer() {
        Customer customer = new Customer();

        String nameString = getString(getColumnIndex(CustomerTable.cols.NAME));
        String numberString = getString(getColumnIndex(CustomerTable.cols.NUMBER));
        long dateLong = getLong(getColumnIndex(CustomerTable.cols.DATE));
        double priceDouble = getDouble(getColumnIndex(CustomerTable.cols.PRICE));
        String productString = getString(getColumnIndex(CustomerTable.cols.PRODUCT));
        String uuidString = getString(getColumnIndex(CustomerTable.cols.UUID));

        customer.setName(nameString);
        customer.setNumber(numberString);
        customer.setDate(new Date(dateLong));
        customer.setPurchasePrice(priceDouble);
        customer.setProduct(productString);
        customer.setUUID(UUID.fromString(uuidString));

        return customer;
    }

}
