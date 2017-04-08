package com.offcasoftware.shop2.model;

import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;

/**
 * Created by krzysztofjanik on 18.03.2017.
 */

public class Contact {

    private static final String COLUMN_NAME =  Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.HONEYCOMB ?
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
            ContactsContract.Contacts.DISPLAY_NAME;

    private final int mId;
    private final String mName;

    public Contact(Cursor cursor) {
        mId = cursor.getInt(cursor.getColumnIndex("_id"));
        mName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
    }

    public Contact(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
