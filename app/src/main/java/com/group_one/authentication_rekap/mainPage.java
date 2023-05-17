package com.group_one.authentication_rekap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainPage extends AppCompatActivity {
    Button review, aboutus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        review = findViewById(R.id.ReviewBtn);
        aboutus = findViewById(R.id.aboutUsBtn);

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainPage.this, inReview.class);
                startActivity(i);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainPage.this, aboutUS.class);
                startActivity(i);
            }
        });

    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Leaving?");
        alertDialog.setMessage("Are you sure you want to exit the app?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
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
}