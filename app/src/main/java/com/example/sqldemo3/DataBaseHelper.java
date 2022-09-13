package com.example.sqldemo3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_ACTIVE_CUSTOMER + " BOOL)";


        db.execSQL(createTableStatement);

    }


    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
        onCreate(db);

    }

    ArrayList<CustomerModel> listContacts() {
        String sql = "select * from " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CustomerModel> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String age = cursor.getString(2);
                storeContacts.add(new CustomerModel(id, name, age));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }



    void addOne(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getPhno());

        db.insert(CUSTOMER_TABLE, null,cv);




    }
    void updateContacts(CustomerModel contacts) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUSTOMER_NAME, contacts.getName());
        values.put(COLUMN_CUSTOMER_AGE, contacts.getPhno());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(CUSTOMER_TABLE, values, COLUMN_ID + " = ?", new String[]{String.valueOf(contacts.getId())});
    }
}
