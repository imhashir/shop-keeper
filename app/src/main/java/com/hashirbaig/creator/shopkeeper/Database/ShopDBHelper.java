package com.hashirbaig.creator.shopkeeper.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hashirbaig.creator.shopkeeper.Database.DatabaseSchema.*;

public class ShopDBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "shopDb.db";
    public static final int VERSION = 1;

    public ShopDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TableCustomers.TABLE_CUSTOMERS + "(" +
                " _id integer primary key autoincrement, " +
                TableCustomers.cols.NAME + ", " +
                TableCustomers.cols.UUID + ", " +
                TableCustomers.cols.NUMBER + ", " +
                TableCustomers.cols.PRICE + ", " +
                TableCustomers.cols.PRODUCT + ", " +
                TableCustomers.cols.DATE +
                ")"
        );

        db.execSQL("create table " + TableProducts.TABLE_PRODUCTS + "(" +
                " _id integer primary key autoincrement, " +
                TableProducts.cols.NAME + ", " +
                TableProducts.cols.UUID + ", " +
                TableProducts.cols.SERIAL + ", " +
                TableProducts.cols.STOCK + ", " +
                TableProducts.cols.PRICE +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
