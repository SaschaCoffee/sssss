package com.example.sqldemo3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvPhoneNum;


    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.contactName);
        tvPhoneNum = itemView.findViewById(R.id.phoneNum);
    }
}
