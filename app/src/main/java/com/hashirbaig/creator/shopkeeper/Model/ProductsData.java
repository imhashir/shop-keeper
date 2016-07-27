package com.hashirbaig.creator.shopkeeper.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hashirbaig.creator.shopkeeper.Database.CursorWrapperHelper;
import com.hashirbaig.creator.shopkeeper.Database.DatabaseSchema;
import com.hashirbaig.creator.shopkeeper.Database.DatabaseSchema.*;
import com.hashirbaig.creator.shopkeeper.Database.ShopDBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductsData {

    private static ProductsData sProductsData;

    private SQLiteDatabase mDatabase;
    private Context mContext;
    private List<Product> mProductList;

    private ProductsData(Context context) {
        mProductList = new ArrayList<>();
        mContext = context.getApplicationContext();
        mDatabase = new ShopDBHelper(mContext).getWritableDatabase();
        readDatabase();
    }

    public static ProductsData get(Context context) {
        if(sProductsData == null) {
            sProductsData = new ProductsData(context);
        }
        return sProductsData;
    }

    public List<Product> getProductList() {
        return mProductList;
    }

    public void setProductList(List<Product> productList) {
        mProductList = productList;
    }

    private void readDatabase() {
        Cursor cursor = mDatabase.query(
                TableProducts.TABLE_PRODUCTS,
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
                    mProductList.add(helper.getProduct());
                    helper.moveToNext();
                }
            } finally {
                helper.close();
            }
        }
    }

    public void add(Product product) {
        mProductList.add(product);
        mDatabase.insert(TableProducts.TABLE_PRODUCTS, null, getContentValues(product));
    }

    public void update(Product product) {
        mDatabase.update(TableProducts.TABLE_PRODUCTS, getContentValues(product), TableProducts.cols.UUID + " = ?", new String[]{product.getUUID().toString()});
    }

    public void delete(Product product) {
        mDatabase.delete(TableProducts.TABLE_PRODUCTS, TableProducts.cols.UUID + " = ?", new String[]{product.getUUID().toString()});
        for(int x = 0; x < mProductList.size(); x++) {
            if(mProductList.get(x).getUUID().equals(product.getUUID())) {
                mProductList.remove(x);
                return;
            }
        }
    }

    public Product get(int position) {
        return mProductList.get(position);
    }

    public ContentValues getContentValues(Product product) {
        ContentValues values = new ContentValues();
        values.put(TableProducts.cols.NAME, product.getName());
        values.put(TableProducts.cols.UUID, product.getUUID().toString());
        values.put(TableProducts.cols.SERIAL, product.getSerialNumber());
        values.put(TableProducts.cols.STOCK, product.getAmountInStock());
        values.put(TableProducts.cols.PRICE, product.getPrice());

        return values;
    }

    public Product find(UUID id) {
        for(Product product : mProductList) {
            if(product.getUUID().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
