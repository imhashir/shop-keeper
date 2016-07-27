package com.hashirbaig.creator.shopkeeper.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.hashirbaig.creator.shopkeeper.Database.DatabaseSchema.*;
import com.hashirbaig.creator.shopkeeper.Model.Customer;
import com.hashirbaig.creator.shopkeeper.Model.Product;

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

        String nameString = getString(getColumnIndex(TableCustomers.cols.NAME));
        String numberString = getString(getColumnIndex(TableCustomers.cols.NUMBER));
        long dateLong = getLong(getColumnIndex(TableCustomers.cols.DATE));
        double priceDouble = getDouble(getColumnIndex(TableCustomers.cols.PRICE));
        String productString = getString(getColumnIndex(TableCustomers.cols.PRODUCT));
        String uuidString = getString(getColumnIndex(TableCustomers.cols.UUID));

        customer.setName(nameString);
        customer.setNumber(numberString);
        customer.setDate(new Date(dateLong));
        customer.setPurchasePrice(priceDouble);
        customer.setProduct(productString);
        customer.setUUID(UUID.fromString(uuidString));

        return customer;
    }

    public Product getProduct() {
        Product product = new Product();

        String nameString = getString(getColumnIndex(TableProducts.cols.NAME));
        String serialString = getString(getColumnIndex(TableProducts.cols.SERIAL));
        String uuidString = getString(getColumnIndex(TableProducts.cols.UUID));
        double priceDouble = getDouble(getColumnIndex(TableProducts.cols.PRICE));
        String stockString = getString(getColumnIndex(TableProducts.cols.STOCK));

        product.setName(nameString);
        product.setSerialNumber(serialString);
        product.setPrice(priceDouble);
        product.setAmountInStock(Integer.parseInt(stockString));
        product.setUUID(UUID.fromString(uuidString));

        return product;
    }

}
