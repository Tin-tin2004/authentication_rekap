package com.group_one.authentication_rekap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group_one.authentication_rekap.Adapter.notesAdapter;
import com.group_one.authentication_rekap.Adapter.notesAdapter;
import com.group_one.authentication_rekap.User;

import java.util.ArrayList;

public class inReview extends AppCompatActivity implements onclickListeners {
    ImageButton back, music, add_notes, timer;
    RecyclerView recyclerView;
    ArrayList<User> list;
    DatabaseReference databaseReference;

    notesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_review);

        back = findViewById(R.id.backbotton);
        add_notes = findViewById(R.id.add_btn);
        music = findViewById(R.id.music_btn);
        timer = findViewById(R.id.timer_btn_id);

        recyclerView = findViewById(R.id.res_view);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new notesAdapter(this, list, this);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inReview.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inReview.this, Music.class);
                startActivity(intent);
                finish();
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(inReview.this, Timer.class);
                startActivity(ii);
                finish();
            }
        });

        add_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii = new Intent(inReview.this, create_notes.class);
                startActivity(iii);
                finish();
            }
        });


    }

    @Override
    public void onItemClick(User user) {




    }

}