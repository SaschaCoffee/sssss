package com.example.sqldemo3;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String GERRAWROH = "GERRAWROH";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";

    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";

    Context contextt;



    public DataBaseHelper(@Nullable Context context) {
        super(context, "customerrrr1.db", null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + GERRAWROH + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_ACTIVE_CUSTOMER + " BOOL)";


        db.execSQL(createTableStatement);




    }


    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + GERRAWROH);
        onCreate(db);

    }

    ArrayList<CustomerModel> listContacts() {
        String sql = "select * from " + GERRAWROH;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CustomerModel> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                String name = cursor.getString(1);

                storeContacts.add(new CustomerModel(name));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }


void csvcopy(Context contex){
    SQLiteDatabase db = this.getWritableDatabase();
    String myCSVFile = "GERRAWROHIDD.csv";
    AssetManager manager = contex.getAssets();
    InputStream inStream = null;
    try {
        inStream = manager.open(myCSVFile);
    }catch (IOException e){
        e.printStackTrace();
    }
    BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
    String line = "";
    db.beginTransaction();
    try{
        while ((line = buffer.readLine()) != null){
            String[] columns = line.split(";");
            if(columns.length != 1) {
                Log.d("CSVParser", "Skipping Bad CSV Row");
                continue;
            }

            ContentValues cv = new ContentValues();


            cv.put(COLUMN_CUSTOMER_NAME, columns[0].trim());


            db.insert(GERRAWROH, null, cv);
        }
    }catch (IOException e){
        e.printStackTrace();
    }
    db.setTransactionSuccessful();
    db.endTransaction();

}

    void addOne(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());


        db.insert(GERRAWROH, null,cv);

    }
    void updateContacts(CustomerModel contacts) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUSTOMER_NAME, contacts.getName());

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(GERRAWROH, values, COLUMN_ID + " = ?", new String[]{String.valueOf(contacts.getId())});
    }
}
