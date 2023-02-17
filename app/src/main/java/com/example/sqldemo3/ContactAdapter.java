package com.example.sqldemo3;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>


implements Filterable {
    private Context context;
    private ArrayList<modelDisplayRv> listContacts;

    private ArrayList<modelDisplayRv> mArrayList;
    private DataBaseHelper mData;

    ContactAdapter(Context context, ArrayList<modelDisplayRv> listContacts){
        this.context = context;
        this.listContacts = listContacts;
        this.mArrayList = listContacts;

        mData = new DataBaseHelper(context);
    }



    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listContacts = mArrayList;
                }
                else {
                    ArrayList<modelDisplayRv> filteredList = new ArrayList<>();
                    for (modelDisplayRv contacts : mArrayList) {
                        if (contacts.getName().toLowerCase().contains(charString)) {
                            filteredList.add(contacts);
                        }
                    }
                    listContacts = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listContacts;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listContacts = (ArrayList<modelDisplayRv>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactistlayout, parent, false);


        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
    final modelDisplayRv contacts = listContacts.get(position);


    holder.tvName.setText(contacts.getName());
    holder.tv_squat.setText(contacts.getSquat());
    holder.tv_bench.setText(contacts.getBench());
    holder.tv_deadlift.setText(contacts.getDeadlift());

      holder.img.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent s = new Intent(context, statisticActivity.class);
              s.putExtra("item", contacts.getPhoneNumber());
              context.startActivity(s);

          }
      });
  ;


    }

    @Override
    public int getItemCount() {
        return listContacts.size();
    }
}
