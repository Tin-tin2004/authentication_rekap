package com.group_one.authentication_rekap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class reg_activity extends AppCompatActivity {
    private EditText mEmail, mPassword;
    private Button mSignIn;
    private TextView mloginHere;
    private ProgressDialog mDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reg);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        registration();


    }

    private void registration() {
        mEmail = findViewById(R.id.Email_text_signin);
        mPassword = findViewById(R.id.Password_text_signin);
        mSignIn = findViewById(R.id.signup_btn);
        mloginHere = findViewById(R.id.login_Here);

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required for this section");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required for this section");
                    return;
                }

                mDialog.setMessage("Processing...");

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration success", Toast.LENGTH_SHORT).show();;
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else {
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mloginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
}