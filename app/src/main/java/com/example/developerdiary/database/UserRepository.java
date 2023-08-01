package com.example.developerdiary.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.developerdiary.login.dto.LoginResponse;
import com.example.developerdiary.login.dto.UsersData;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public UserRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addUser(String email, String access_token,String refresh_token
    ,String firstName,String lastname) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_ACCESS_TOKEN,access_token);
        values.put(DatabaseHelper.COLUMN_REFRESH_TOKEN,refresh_token);
        values.put(DatabaseHelper.COLUMN_FIRSTNAME,firstName);
        values.put(DatabaseHelper.COLUMN_LASTNAME,lastname);
        return database.insert(DatabaseHelper.TABLE_CREDENTIALS, null, values);
    }

    public LoginResponse getAllUsers() {
        LoginResponse userList = new LoginResponse();

        Cursor cursor = database.query(DatabaseHelper.TABLE_CREDENTIALS,
                null, null, null, null, null, null);

        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            LoginResponse user = cursorToUser(cursor);
//            userList = user;
////            cursor.moveToNext();
//        }
        LoginResponse user = cursorToUser(cursor);
        cursor.close();
        return user;
    }

    @SuppressLint("Range")
    private LoginResponse cursorToUser(Cursor cursor) {
        LoginResponse user = new LoginResponse();
        user.setUser(new UsersData(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID))
                ,cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL))
                ,cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRSTNAME))
                ,cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LASTNAME))));
       return user;
    }
}
