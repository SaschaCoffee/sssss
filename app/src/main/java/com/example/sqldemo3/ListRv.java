package com.example.sqldemo3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListRv extends AppCompatActivity {
    private DataBaseHelper mDatabase;
    Button add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        Button add = findViewById(R.id.btnAdd);
        Button finaladd = findViewById(R.id.btn_add);

        RecyclerView contactView = findViewById(R.id.myContactList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contactView.setLayoutManager(linearLayoutManager);
        contactView.setHasFixedSize(true);
        mDatabase = new DataBaseHelper(this);
        ArrayList<CustomerModel> allContacts = mDatabase.listContacts();
        if(allContacts.size() > 0) {
            contactView.setVisibility(View.VISIBLE);
            ContactAdapter mAdapter = new ContactAdapter(this, allContacts);
            contactView.setAdapter(mAdapter);
        }
        else {
            contactView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no contact in the database. Start adding now", Toast.LENGTH_LONG).show();
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListRv.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
