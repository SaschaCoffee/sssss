package com.example.sqldemo3;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListRv extends AppCompatActivity {
    public static final String COLUMN_ID = "ID";
    public static final String GERRRAWROH = "GERRRAWROH";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";

    private DataBaseHelper mDatabase;
    SQLiteDatabase db;
    Button sbd, add, btn_logregister;
    TextView tv_banner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);


        Button add = findViewById(R.id.btnAdd);
        Button finaladd = findViewById(R.id.btn_add);
        sbd = findViewById(R.id.sbd_button);
        btn_logregister = findViewById(R.id.btn_register_log);



        RecyclerView contactView = findViewById(R.id.myContactList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contactView.setLayoutManager(linearLayoutManager);
        contactView.setHasFixedSize(true);


        mDatabase = new DataBaseHelper(this);
        ArrayList<modelDisplayRv> allContacts = mDatabase.listContacts();
        if(allContacts.size() > 0) {
            contactView.setVisibility(View.VISIBLE);
            ContactAdapter mAdapter = new ContactAdapter(this, allContacts);
            contactView.setAdapter(mAdapter);
        }
        else {
            Toast.makeText(this, "HALOOOOO", Toast.LENGTH_LONG).show();
            mDatabase.csvcopy(this);
            contactView.setVisibility(View.VISIBLE);
            ArrayList<modelDisplayRv> allContactss = mDatabase.listContacts();
            ContactAdapter mAdapter = new ContactAdapter(this, allContactss);
            contactView.setAdapter(mAdapter);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListRv.this, searchActivity.class);
                startActivity(intent);
            }
        });

        sbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListRv.this, cardMainactivity.class);
                startActivity(intent);
            }
        });

        btn_logregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListRv.this, logRegActivity.class);
                startActivity(intent);
            }
        });




    }



}
