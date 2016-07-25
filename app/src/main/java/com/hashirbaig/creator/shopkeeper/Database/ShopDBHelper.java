package com.hashirbaig.creator.shopkeeper.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hashirbaig.creator.shopkeeper.Database.DatabaseSchema.CustomerTable;

public class ShopDBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "shopDb.db";
    public static final int VERSION = 1;

    public ShopDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CustomerTable.TABLE_NAME + "(" +
                " _id integer primary key autoincrement, " +
                CustomerTable.cols.NAME + ", " +
                CustomerTable.cols.UUID + ", " +
                CustomerTable.cols.NUMBER + ", " +
                CustomerTable.cols.PRICE + ", " +
                CustomerTable.cols.PRODUCT + ", " +
                CustomerTable.cols.DATE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
