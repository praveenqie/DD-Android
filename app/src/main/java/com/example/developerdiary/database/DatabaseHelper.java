package com.example.developerdiary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user_credentials.db";
    public static final int DATABASE_VERSION = 2;

    // Table name and column names
    public static final String TABLE_CREDENTIALS = "credentials";
    public static final String COLUMN_ID = "id";

    public static final String COLUMN_ACCESS_TOKEN = "access_token";

    public static final String COLUMN_REFRESH_TOKEN = "refresh_token";
    public static final String COLUMN_EMAIL= "email";

    public static final String COLUMN_FIRSTNAME= "firstname";

    public static final String COLUMN_LASTNAME = "lastname";
    private static final String CREATE_TABLE_CREDENTIALS = "CREATE TABLE " + TABLE_CREDENTIALS +
            "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_EMAIL + " TEXT NOT NULL," +
            COLUMN_ACCESS_TOKEN + " TEXT NOT NULL,"+
            COLUMN_REFRESH_TOKEN + " TEXT NOT NULL,"+
            COLUMN_FIRSTNAME + " TEXT NOT NULL,"+
            COLUMN_LASTNAME + " TEXT NOT NULL"+
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CREDENTIALS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema upgrades here if needed

        if (oldVersion < 2) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDENTIALS);
            onCreate(db);
        }
    }
}
