package com.offcasoftware.shop2;

import com.offcasoftware.shop2.database.Database;
import com.offcasoftware.shop2.database.DatabaseImpl;

import android.app.Application;


/**
 * @author maciej.pachciarek on 2017-03-06.
 */

public class AndroidApplication extends Application {

    private static Database mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = new DatabaseImpl(this);
        //mDatabase = OpenHelperManager.getHelper(
          //      this, DatabaseOrmImpl.class);
        //((DatabaseImpl) mDatabase).getWritableDatabase();
        //try {
            //((DatabaseOrmImpl) mDatabase)
          //          .getConnectionSource()
              //      .getReadWriteConnection();
        //} catch (SQLException e) {
          //  e.printStackTrace();
        //}
    }

    public static Database getDatabase() {
        return mDatabase;
    }
}
