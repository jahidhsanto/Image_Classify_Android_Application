package com.example.imageclassify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth fAuth;
//    private FirebaseFirestore fStore;
    private String userID;
    private EditText mfullName, memail, mpassword;
    private Button mbtnSignUp;
    private TextView mgotoLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mfullName = findViewById(R.id.inputFullName);
        memail = findViewById(R.id.inputEmail);
        mpassword = findViewById(R.id.inputPassword);
        mbtnSignUp = findViewById(R.id.btnSignUp);
        mgotoLogIn = findViewById(R.id.gotoLogIn);

        // Initialize Firebase Auth
        fAuth = FirebaseAuth.getInstance();

        // Initialize Firebase Auth
        fAuth = FirebaseAuth.getInstance();

        mbtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = mfullName.getText().toString().trim();
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                if ((TextUtils.isEmpty(fullName)) || (TextUtils.isEmpty(email)) || (TextUtils.isEmpty(password))) {
                    Toast.makeText(SignUp.this, "All fields are Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // register the user in firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Dashboard.class));


                            userID = fAuth.getCurrentUser().getUid();
//                            CollectionReference collectionReference = fStore.collection("users").document(userID).collection("profileInformation");
//                            // Create a new user with a first, middle, and last name
//                            Map<String, Object> user = new HashMap<>();
//                            user.put("fName", fullName);
//                            user.put("email", email);
//
//                            // Add a new document with a generated ID
//                            collectionReference.document("profileInformation").set(user).addOnSuccessListener(documentReference -> {
//                                // Document added successfully
//                                startActivity(new Intent(getApplicationContext(), Dashboard.class));
//                                Log.d("TAG", "onSuccess: user Profile is created for " + userID);
//
//                            }).addOnFailureListener(e -> {
//                                // Error adding document
//                                Log.e("TAG", "Error adding document", e);
//                            });
                        } else {
                            Toast.makeText(SignUp.this, "Error: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mgotoLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}