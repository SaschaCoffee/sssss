package com.example.sqldemo3;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class profileListHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvPhoneNum, tv_squat, tv_bench, tv_deadlift;
    ImageView img;
    Button button;



    public profileListHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.contactName);
        tvPhoneNum = itemView.findViewById(R.id.phoneNum);
        img = itemView.findViewById(R.id.editContact);
        tv_squat = itemView.findViewById(R.id.tv_squat);
        tv_bench = itemView.findViewById(R.id.tv_bench);
        tv_deadlift = itemView.findViewById(R.id.tv_deadlift);
        button = itemView.findViewById(R.id.bebe);



       tv_bench.setClickable(true);
       tv_bench.setFocusableInTouchMode(true);

        itemView.setClickable(true);
        itemView.setFocusableInTouchMode(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "AAAAAAAAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
                Log.d("tam","");
            }
        });

       tv_bench.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(view.getContext(), "AAAAAAAAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
               Log.d("tam","");
           }
       });

        
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(itemView.getContext(), "BREEEEEEEEEEEEEEEEEEEEE", Toast.LENGTH_SHORT).show();
                Log.d("tam","");
            }
        });
    }


}
