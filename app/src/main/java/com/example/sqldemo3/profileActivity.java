package com.example.sqldemo3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference, referencePost, referencepost2;
    TextView tv_name, tv_email, tv_age, tv_getter;
    private String userid;
    private Button btn_firebase;
    private EditText et_fire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_name = findViewById(R.id.tv_profile_fullname);
        tv_email = findViewById(R.id.tv_profile_email);
        tv_age = findViewById(R.id.tv_profile_age);
        tv_getter = findViewById(R.id.tv_getterFb);
        btn_firebase = findViewById(R.id.btn_firebase);
        et_fire = findViewById(R.id.et_firebase);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userid = user.getUid();
        String userid2 = "XUebp5nAiYRpYeDU85wdwRVTBa42";
        String fuck = "users";

        referencePost = FirebaseDatabase.getInstance().getReference("posts");
        referencepost2 = FirebaseDatabase.getInstance().getReference("users");

        referencepost2.child(fuck).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stringModel xx = snapshot.getValue((stringModel.class));
                tv_getter.setText(xx.benchPress);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child(userid2).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userModel userProfile = snapshot.getValue(userModel.class);

                if(userProfile != null){

                    String fullname = userProfile.fullName;
                    String age = userProfile.age;
                    String email = userProfile.email;


                    tv_name.setText(fullname);
                    tv_email.setText(email);
                    tv_age.setText(age);
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Toast.makeText(profileActivity.this, "ww", Toast.LENGTH_SHORT).show();
            }
        });





        btn_firebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "hallo";

                referencepost2.child("users").child("benchPress").setValue("300");
            }
        });

    }


}