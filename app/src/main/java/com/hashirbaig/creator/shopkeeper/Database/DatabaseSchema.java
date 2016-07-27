package com.hashirbaig.creator.shopkeeper.Database;

public class DatabaseSchema {

    public static final class TableCustomers {
        public static final String TABLE_CUSTOMERS = "customers";

        public static final class cols {
            public static final String NAME = "name";
            public static final String NUMBER = "number";
            public static final String UUID = "uuid";
            public static final String DATE = "date";
            public static final String PRICE = "price";
            public static final String PRODUCT = "product";
        }
    }

    public static final class TableProducts {
        public static final String TABLE_PRODUCTS = "products";

        public static final class cols {
            public static final String NAME = "name";
            public static final String SERIAL = "number";
            public static final String UUID = "uuid";
            public static final String STOCK = "date";
            public static final String PRICE = "price";
        }
    }

}
