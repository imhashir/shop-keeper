package com.hashirbaig.creator.shopkeeper.Helper;

import android.os.Environment;
import android.text.format.DateFormat;

import com.hashirbaig.creator.shopkeeper.Database.ShopDBHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class BackupRestore {

    private static final String mAppDirectory
            = "/data/data/com.hashirbaig.creator.shopkeeper/databases/";

    private static final String mUserDirectory
            = Environment.getExternalStorageDirectory() + "/ShopKeeper/";

    public static class Local {
        public static void backupDatabase() throws IOException {
            File appFile = new File(mAppDirectory + ShopDBHelper.DATABASE_NAME);
            FileInputStream fis = new FileInputStream(appFile);

            File outputFolder = new File(mUserDirectory);
            if(!outputFolder.exists()) {
                outputFolder.mkdir();
            }

            String dateFormat = DateFormat.format("yyyy-MM-dd_HH-mm-ss", new Date()).toString();
            OutputStream fos = new FileOutputStream(mUserDirectory + dateFormat + ".db", true);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fos.flush();
            fos.close();
            fis.close();
        }

        public static void restoreDatabase() throws IOException{
            File userFiles = new File(mUserDirectory);
            File[] files = userFiles.listFiles();
            FileInputStream fis = new FileInputStream(files[0]);

            File outputFolder = new File(mAppDirectory);
            if(!outputFolder.exists()) {
                outputFolder.mkdir();
            }
            FileOutputStream fos = new FileOutputStream(mAppDirectory + ShopDBHelper.DATABASE_NAME);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fos.flush();
            fos.close();
            fis.close();
        }

    }

}
