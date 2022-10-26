package com.example.sqldemo3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cardMainactivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    ArrayList<greenCardModel> lstBook = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar pgBar;
    private TextView tv;
    int progr;
    private Button btn_add_data, btn_upload_data;
    private AlertDialog dialog;
    private String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitycard);
        pgBar = findViewById(R.id.progress_bar);
        tv = findViewById(R.id.text_view_progress);
        btn_add_data = findViewById(R.id.btn_add_data);
        String childcard = "anzahl";

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("cardprogress");
        userid = user.getUid();




        reference.child(childcard).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            try {
                long model = snapshot.getValue(long.class);
                progr = Integer.parseInt(String.valueOf(model));

                updateCard(progr);
                buildRecyclerView();
            }catch (Exception e){
                Toast.makeText(cardMainactivity.this, "dd", Toast.LENGTH_SHORT).show();
            }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Data");

        View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);

        btn_upload_data = view.findViewById(R.id.btn_upload_data);

        createCard();
        buildRecyclerView();
        builder.setView(view);
        dialog = builder.create();

        btn_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        btn_upload_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progr++;
                reference.child("anzahl").setValue(progr);
                updateProgressBar(progr);
                updateCard(progr);
                buildRecyclerView();
                dialog.dismiss();

            }
        });

    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview_id);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new cardViewAdapter(this, lstBook);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void updateCard(int z) {
        int countstart = z;
        for (int x = 0; x < z; x++) {
            lstBook.set(x, new greenCardModel(R.drawable.quadratgruen));
            Toast.makeText(this, "" + countstart, Toast.LENGTH_SHORT).show();
        }

    }


    private void createCard() {
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));


        int arraySize = lstBook.size();
        updateProgressBar(arraySize);


    }

    private void updateProgressBar(int z) {
        int x = z;
        String zy = String.valueOf(z) + "%";

        pgBar.setProgress(x);
        tv.setText(zy);


    }

}