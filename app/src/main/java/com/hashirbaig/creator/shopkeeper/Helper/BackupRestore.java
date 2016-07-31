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

    public static final String APP_DIRECTORY
            = "/data/data/com.hashirbaig.creator.shopkeeper/databases/";

    public static final String USER_DIRECTORY
            = Environment.getExternalStorageDirectory() + "/ShopKeeper/Backups/";

    public static class Local {
        public static void backupDatabase() throws IOException {
            File appFile = new File(APP_DIRECTORY + ShopDBHelper.DATABASE_NAME);
            FileInputStream fis = new FileInputStream(appFile);

            File outputFolder = new File(USER_DIRECTORY);
            if(!outputFolder.exists()) {
                outputFolder.mkdir();
            } else {
                File[] currentFile = outputFolder.listFiles();
                if(currentFile.length > 0) {
                    currentFile[0].delete();
                }
            }


            String dateFormat = DateFormat.format("yyyy-MM-dd_HH-mm-ss", new Date()).toString();
            OutputStream fos = new FileOutputStream(USER_DIRECTORY + dateFormat + ".db", true);
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
            File userFiles = new File(USER_DIRECTORY);
            File[] files = userFiles.listFiles();
            FileInputStream fis = new FileInputStream(files[0]);

            File outputFolder = new File(APP_DIRECTORY);
            if(!outputFolder.exists()) {
                outputFolder.mkdir();
            }
            FileOutputStream fos = new FileOutputStream(APP_DIRECTORY + ShopDBHelper.DATABASE_NAME);
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
