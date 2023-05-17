package com.group_one.authentication_rekap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create_notes extends AppCompatActivity {

    ImageButton insert, viewNback;
    EditText title, notes;
    DatabaseReference databaseUsers;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(create_notes.this, inReview.class));
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);

        insert = findViewById(R.id.save);
        viewNback = findViewById(R.id.back_btn);
        title = findViewById(R.id.edittext_title);
        notes = findViewById(R.id.edittext_notes);

        databaseUsers = FirebaseDatabase.getInstance().getReference();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(create_notes.this, inReview.class));
                finish();

                InsertData();
            }
        });

        viewNback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(create_notes.this, inReview.class));
                finish();
            }
        });


    }

    private void InsertData() {
        String usersTitle = title.getText().toString();
        String usersNotes = notes.getText().toString();
        String id = databaseUsers.push().getKey();

        User users = new User(usersTitle, usersNotes);
        databaseUsers.child("users").child(id).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(create_notes.this, "saved", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(create_notes.this, "did not saved", Toast.LENGTH_SHORT).show();

                        }
                    }


                });
    }

    public void actionMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_option, popupMenu.getMenu());
        popupMenu.show();
    }


    public void del(MenuItem item) {
        Toast.makeText(this, "delete clicked", Toast.LENGTH_SHORT).show();

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("DElETE");
            alertDialog.setMessage("are you sure you want to delete this note?");
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteFile(
                            String.valueOf(databaseUsers.get()));
                }
            });
            alertDialog.setNegativeButton( "NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }


            });
            AlertDialog builder = alertDialog.create();
            builder.show();
            builder.getButton(builder.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.teal_eme));
            builder.getButton(builder.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.teal_eme));



    }

    public void addFile(MenuItem item) {
        Toast.makeText(this, "add files clicked", Toast.LENGTH_SHORT).show();

    }

    public void addImg(MenuItem item) {
        Toast.makeText(this, "add image clicked", Toast.LENGTH_SHORT).show();

    }

    public void bkmark(MenuItem item) {
        Toast.makeText(this, "bookmark clicked", Toast.LENGTH_SHORT).show();

    }
}