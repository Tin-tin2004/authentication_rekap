package com.group_one.authentication_rekap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.group_one.authentication_rekap.R;
import com.group_one.authentication_rekap.User;
import com.group_one.authentication_rekap.onclickListeners;

import java.util.ArrayList;

public class notesAdapter extends RecyclerView.Adapter<notesAdapter.notesViewHolder>{
    private final onclickListeners onclickListeners;
    Context context;
    ArrayList<User> list;
    private onclickListeners listener;

    public notesAdapter(Context context, ArrayList<User> list, onclickListeners listener ) {
        this.context = context;
        this.list = list;
        this.onclickListeners = listener;
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_save_view, parent, false);
        return new notesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {

        User user = list.get(position);
        holder.title.setText(user.getTitle());
        holder.notes.setText(user.getNotes());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class notesViewHolder extends RecyclerView.ViewHolder {
        TextView title, notes;
        CardView cardView;
        public notesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
            notes = itemView.findViewById(R.id.txt_notes);
            cardView = itemView.findViewById(R.id.notes_container);


        }
    }
}
