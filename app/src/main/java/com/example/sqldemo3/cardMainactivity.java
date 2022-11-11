package com.example.sqldemo3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Arrays;

//ANSATZ nach erhöhung und button speichern-> in array anzahl abspeichern. dann switch anweisung case array.size=1, tv.set, anzahl
    public class cardMainactivity extends AppCompatActivity {

        private FirebaseUser user;
        private DatabaseReference reference, referenceTest;
        ArrayList<greenCardModel> lstBook = new ArrayList<>();
        ArrayList<Integer> jd = new ArrayList<>();
        ArrayList<Long> firebaseArray;

        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;
        private ProgressBar pgBar;
        private TextView tv, tv_updown, tv_updown_third;
        private EditText et_first_set, et_second_set, et_third_set;
        int progr;
        private Button btn_add_data, btn_upload_data, btn_addtoTv, btntoTv;
        private AlertDialog dialog;
        private String userid;
        private int counter = 0;
        KeyEvent cc;
        int x = 1;
        int arraycounter = 0;
        long size = 4;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activitycard);
            pgBar = findViewById(R.id.progress_bar);
            tv = findViewById(R.id.text_view_progress);
            btn_add_data = findViewById(R.id.btn_add_data);
            String childcard = "anzahl";
            String childcardBench = "Best3BenchKg";
            String childcardDate = "Date";


            user = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference("cardprogress");
            referenceTest = FirebaseDatabase.getInstance().getReference("teste");
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

            referenceTest.child(childcardDate).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    long count = snapshot.getChildrenCount();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            firebaseArray = new ArrayList<>();


            referenceTest.child(childcardBench).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        long xxx = dataSnapshot.getValue(long.class);
                        firebaseArray.add(xxx);
                    }
                    Toast.makeText(cardMainactivity.this, "size:" + firebaseArray.size(), Toast.LENGTH_SHORT).show();

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            dialogExtend xx = new dialogExtend();
            builder.setTitle("Enter Data");

            View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);

            btn_upload_data = view.findViewById(R.id.btn_upload_data);
            tv_updown = view.findViewById(R.id.tv_updown);
            tv_updown_third = view.findViewById(R.id.tv_reps_third);
            btntoTv = view.findViewById(R.id.button39);
            et_first_set = view.findViewById(R.id.et_symbol_reps);
            et_second_set = view.findViewById(R.id.et_symbol_reps2);
            et_third_set = view.findViewById(R.id.et_symbol_reps3);



            et_first_set.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(cardMainactivity.this, "31232", Toast.LENGTH_SHORT).show();
                }
            });


            DialogInterface.OnKeyListener xxy = new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    String x = "1";

                       if(tv_updown_third.equals(String.class)){
                           Toast.makeText(cardMainactivity.this, "111111", Toast.LENGTH_SHORT).show();
                       }

                    int code = keyEvent.getKeyCode();

                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        Toast.makeText(cardMainactivity.this, "1down", Toast.LENGTH_SHORT).show();

                        switch (code) {
                            case KeyEvent.KEYCODE_VOLUME_UP:
                                Toast.makeText(cardMainactivity.this, "up", Toast.LENGTH_SHORT).show();
                                return true;
                            case KeyEvent.KEYCODE_VOLUME_DOWN:
                                counter++;
                                tv_updown_third.setText(String.valueOf(counter));
                                return true;
                        }
                    }
                    return true;
                }


            };



            builder.setOnKeyListener(xxy);

            createCard();
            buildRecyclerView();
            builder.setView(view);
            dialog = builder.create();

            btntoTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 addtoTv();
                }
            });


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


    private void addtoTv() {

            arraycounter++;

            switch(arraycounter){
                case 1:
                    Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                    et_first_set.setText(String.valueOf(counter));
                    this.counter = 0;
                    break;
                case 2:
                    Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                    et_second_set.setText(String.valueOf(counter));
                    this.counter = 0;
                    break;
                case 3:
                    et_third_set.setText(String.valueOf(counter));
                    this.counter = 0;
                    this.arraycounter = 0;
                    break;
            }

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
