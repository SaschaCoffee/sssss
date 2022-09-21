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
    public static final String COLUMN_NAME = "column_name";
    public static final String COLUMN_OPENPL = "column_openpl";
    public static final String COLUMN_INSTA = "column_insta";
    public static final String COLUMN_BUNDESLAND = "column_bundesland";
    public static final String COLUMN_VERBAND = "column_verband";
    public static final String COLUMN_DATUM = "column_datum";
    public static final String COLUMN_ORT = "column_ort";
    public static final String COLUMN_GESCHLECHT = "column_geschlecht";
    public static final String COLUMN_ALTER = "column_alter";
    public static final String COLUMN_SQUAT = "column_squat";
    public static final String COLUMN_BENCH = "column_bench";
    public static final String COLUMN_DEADLIFT = "column_deadlift";
    public static final String GERMANY = "GERMANY";
    public static final String COLUMN_ID = "COLUMN_ID";
    private String land = "Bundesland";
    Context contextt;





    public DataBaseHelper(@Nullable Context context) {
        super(context, "cukstomkkkkerrrr1.db", null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        String x = "CREATE TABLE " + GERMANY + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_OPENPL + " TEXT, " + COLUMN_INSTA + " TEXT, " + COLUMN_BUNDESLAND + " TEXT, " + COLUMN_VERBAND + " TEXT, " + COLUMN_DATUM + " REAL, " + COLUMN_ORT + " TEXT, " + COLUMN_GESCHLECHT + " TEXT, " + COLUMN_ALTER + " INT, " + COLUMN_SQUAT + " REAL, " + COLUMN_BENCH + " REAL, " + COLUMN_DEADLIFT + " REAL)";

        db.execSQL(x);



    }


    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + GERMANY);
        onCreate(db);

    }

    ArrayList<ModelAdd> listContacts() {
        String sql = "select * from " + GERMANY + " WHERE " + COLUMN_BUNDESLAND + "='BY'";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ModelAdd> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                    int id = Integer.parseInt(cursor.getString(0));
                    String name = cursor.getString(1);
                    String age = cursor.getString(2);
                    storeContacts.add(new ModelAdd(id, name, age));

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }


void csvcopy(Context contex){
    SQLiteDatabase db = this.getWritableDatabase();
    String myCSVFile = "GerPounds.csv";
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
            if(columns.length != 12) {
                Log.d("CSVParser", "Skipping Bad CSV Row");
                continue;
            }

            ContentValues cv = new ContentValues();

            cv.put(COLUMN_NAME, columns[0].trim());
            cv.put(COLUMN_OPENPL, columns[1].trim());
            cv.put(COLUMN_INSTA, columns[2].trim());
            cv.put(COLUMN_BUNDESLAND, columns[3].trim());
            cv.put(COLUMN_VERBAND, columns[4].trim());
            cv.put(COLUMN_DATUM, columns[5].trim());
            cv.put(COLUMN_ORT, columns[6].trim());
            cv.put(COLUMN_GESCHLECHT, columns[7].trim());
            cv.put(COLUMN_ALTER, columns[8].trim());
            cv.put(COLUMN_SQUAT, columns[9].trim());
            cv.put(COLUMN_BENCH, columns[10].trim());
            cv.put(COLUMN_DEADLIFT, columns[11].trim());



            db.insert(GERMANY, null, cv);
        }
    }catch (IOException e){
        e.printStackTrace();
    }
    db.setTransactionSuccessful();
    db.endTransaction();

}

    void addOne(ModelAdd modeladd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, modeladd.getName());


        db.insert(GERMANY, null,cv);

    }
    void updateContacts(CustomerModel contacts) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contacts.getName());
        values.put(COLUMN_OPENPL, contacts.getOpenpl());
        values.put(COLUMN_INSTA, contacts.getInsta());
        values.put(COLUMN_BUNDESLAND, contacts.getBundes());
        values.put(COLUMN_VERBAND, contacts.getVerband());
        values.put(COLUMN_DATUM, contacts.getDatum());
        values.put(COLUMN_ORT, contacts.getOrt());
        values.put(COLUMN_GESCHLECHT, contacts.getGeschlecht());
        values.put(COLUMN_ALTER, contacts.getAlter());
        values.put(COLUMN_SQUAT, contacts.getSquat());
        values.put(COLUMN_BENCH, contacts.getBench());
        values.put(COLUMN_DEADLIFT, contacts.getDeadlift());

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(GERMANY, values, COLUMN_ID + " = ?", new String[]{String.valueOf(contacts.getId())});
    }
}
