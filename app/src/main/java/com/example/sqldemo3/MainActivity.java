package com.example.sqldemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_viewAll;
    EditText et_name, et_age;
    Switch sw_activeCustomer;
    ListView lv_customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        sw_activeCustomer = findViewById(R.id.sw_active);
        lv_customerList = findViewById(R.id.lv_customerList);

        btn_add.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {

             final String name = et_name.getText().toString();
             final String ph_no = et_age.getText().toString();

             ModelAdd modeladd;
             DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

             try {
                 modeladd = new ModelAdd(-1, name, ph_no);
                 dataBaseHelper.addOne(modeladd);

                 Toast.makeText(MainActivity.this, "ADD BUTTON", Toast.LENGTH_SHORT).show();


             } catch (Exception e) {
                 Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

             }

         }
        }


        );

        btn_viewAll.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "geht", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
