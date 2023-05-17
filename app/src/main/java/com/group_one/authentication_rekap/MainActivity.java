package com.group_one.authentication_rekap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //log in layout page
    private EditText memail , mpssword;
    private Button mlogin;
    private TextView msignupHere;
    private ProgressDialog mDialog;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        loginDetail();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, mainPage.class));
            finish();
        }
    }

    private void loginDetail(){
        memail = findViewById(R.id.Email_text);
        mpssword = findViewById(R.id.Password_text);
        mlogin = findViewById(R.id.login_btn);
        msignupHere = findViewById(R.id.signin_here);

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpssword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is needed");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mpssword.setError("Password is needed");
                    return;
                }
                mDialog.setMessage("Processing...");
                mDialog.show();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(getApplicationContext(), "Log in Successful", Toast.LENGTH_SHORT).show();
                        }else {
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Log in Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        msignupHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),reg_activity.class));
            }
        });




    }


}