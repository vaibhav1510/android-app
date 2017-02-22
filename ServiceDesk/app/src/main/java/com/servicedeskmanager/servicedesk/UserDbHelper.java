package com.servicedeskmanager.servicedesk;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cb-vaibhav on 19/02/17.
 */

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dmx_db";
    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE= "CREATE TABLE IF NOT EXISTS login_tables(username VARCHAR,password VARCHAR);";

    public UserDbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        Log.e("DATABASE OPERATIONS", "created/open...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.e("DATABASE OPERATIONS", "created table...");
    }

    public void dbInsert(SQLiteDatabase db, String username, String password){
        ContentValues vals = new ContentValues();
        vals.put("username",username);
        vals.put("password",password);
        db.insert("login_tables", null, vals);
        Log.e("DATABASE OPERATIONS", "one row insert...");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
