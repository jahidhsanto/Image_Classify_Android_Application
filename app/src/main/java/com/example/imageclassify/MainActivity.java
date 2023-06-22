package com.example.imageclassify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
//import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth fAuth;
    //    private FirebaseFirestore fStore;
    private String userID;
    private EditText memail, mpassword;
    private Button mbtnLogin;
    private TextView mgotoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memail = findViewById(R.id.inputEmail);
        mpassword = findViewById(R.id.inputPassword);
        mbtnLogin = findViewById(R.id.btnLogin);
        mgotoRegister = findViewById(R.id.gotoRegister);

        fAuth = FirebaseAuth.getInstance();

        // Check if user is signed in (non-null)
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            finish();
        }

        mbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                if ((TextUtils.isEmpty(email)) || (TextUtils.isEmpty(password))) {
                    Toast.makeText(MainActivity.this, "All fields are Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // authentice the user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mgotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

    }
}