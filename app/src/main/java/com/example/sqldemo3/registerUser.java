package com.example.sqldemo3;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class registerUser extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText et_name, et_age, et_email, et_pw;
    private TextView banner;
    private Button btn_register;
    private ProgressBar pg_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeractivity);

        mAuth = FirebaseAuth.getInstance();
        pg_bar = findViewById(R.id.progressBar);
        et_name = findViewById(R.id.et_register_name);
        et_age = findViewById(R.id.et_register_age);
        et_email = findViewById(R.id.et_register_email);
        et_pw = findViewById(R.id.et_register_password);

        banner = findViewById(R.id.banner);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }



    public void registerUser() {
        String name = et_name.getText().toString().trim();
        String age = et_age.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String pw = et_pw.getText().toString().trim();

        if(name.isEmpty()){
            et_name.setError("Full name is required");
            et_name.requestFocus();
            return;
        }

        if(age.isEmpty()){
            et_age.setError("Age is required");
            et_age.requestFocus();
            return;
        }

        if(email.isEmpty()){
            et_email.setError("Email is required");
            et_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Please provide valid email");
            et_email.requestFocus();
            return;
        }

        if(pw.isEmpty()){
            et_pw.setError("Pw is required");
            et_pw.requestFocus();
            return;
        }

        if(pw.length() < 6) {
            et_pw.setError("Min pw length should be 6 characters");
            et_pw.requestFocus();
            return;
        }

        pg_bar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(registerUser.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                   userModel user = new userModel(name, age, email);
                   FirebaseDatabase.getInstance().getReference("users")
                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                           .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){
                                       Toast.makeText(registerUser.this, "SUCCESS REGISTER", Toast.LENGTH_SHORT).show();
                                       pg_bar.setVisibility(View.GONE);
                                   }else{
                                       Toast.makeText(registerUser.this, "FAILED TO REGISTER", Toast.LENGTH_SHORT).show();

                                   }
                               }
                           });

                }else{
                    Toast.makeText(registerUser.this, "FFF", Toast.LENGTH_SHORT).show();
                    Toast.makeText(registerUser.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    @Override
    public void onClick(View view) {

    }
}
