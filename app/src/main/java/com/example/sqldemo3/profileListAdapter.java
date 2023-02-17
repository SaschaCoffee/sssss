package com.example.sqldemo3;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class profileListAdapter extends RecyclerView.Adapter<profileListHolder>


implements Filterable {
    private Context context;
    private ArrayList<modelDisplayRv> listContacts;
    private ArrayList<modelDisplayRv> mArrayList;
    private RecyclerView xx;

    profileListAdapter(Context context, ArrayList<modelDisplayRv> listContacts, RecyclerView contactView){
        this.context = context;
        this.listContacts = listContacts;
        this.mArrayList = listContacts;
        this.xx = contactView;
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
    public profileListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactistlayout, parent, false);
        context = parent.getContext();
        return new profileListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull profileListHolder holder, int position) {
    final modelDisplayRv contacts = listContacts.get(position);


    holder.tvName.setText(contacts.getName());
    holder.tv_squat.setText(contacts.getSquat());
    holder.tv_bench.setText(contacts.getBench());
    holder.tv_deadlift.setText(contacts.getDeadlift());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(context, "fwefweewfewewffewewf", Toast.LENGTH_SHORT).show();
        }
    });

    holder.itemView.findViewById(R.id.tv_bench).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(context, "BREWEEEEEEEEEEEEEEEEE", Toast.LENGTH_SHORT).show();
            Toast.makeText(holder.tv_bench.getContext(), "eeeeeeeeeeeeeeeee", Toast.LENGTH_SHORT).show();

            Log.d("hallo","" + context);
        }
    });

    holder.button.setClickable(true);
    holder.button.setFocusableInTouchMode(true);


    holder.button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {



            Toast.makeText(view.getContext().getApplicationContext(), "hhhhhhhhhhhhhhhhhhhhhhhhh", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "333333333333333333333333333333333333", Toast.LENGTH_SHORT).show();

            Log.d("tete","");






        }
    });





      holder.img.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(context.getApplicationContext(), "ssssssssssssssss", Toast.LENGTH_SHORT).show();
              Toast.makeText(view.getContext(), "hhhhhhhhhhhhhhhhhhhhhhhhh", Toast.LENGTH_SHORT).show();
              Toast.makeText(context, "333333333333333333333333333333333333", Toast.LENGTH_SHORT).show();

              Log.d("pepe","");

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
