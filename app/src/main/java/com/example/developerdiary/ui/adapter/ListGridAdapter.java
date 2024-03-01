package com.example.developerdiary.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerdiary.R;
import com.example.developerdiary.ui.dto.Tutorial;

import java.util.ArrayList;

public class ListGridAdapter extends RecyclerView.Adapter<ListGridAdapter.MyViewHolder> {
    private ArrayList<Tutorial> tutorialList;

    public ListGridAdapter(ArrayList<Tutorial> tutorialList) {
        this.tutorialList = tutorialList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tutorial tutorial = tutorialList.get(position);
        holder.title.setText(tutorial.getTitle());
       // holder.description.setText(tutorial.getDescription());
    }

    @Override
    public int getItemCount() {
        return tutorialList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
           // description = itemView.findViewById(R.id.description);
        }
    }
}
