package com.example.imageclassify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//import com.google.android.gms.maps.model.Dash;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.FirebaseFirestore;

public class Dashboard extends AppCompatActivity {
    private FirebaseAuth fAuth;
//    private FirebaseFirestore fStore;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fAuth = FirebaseAuth.getInstance();


        findViewById(R.id.Home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, "You are on Home", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this, Dashboard.class));
            }
        });

        findViewById(R.id.AboutId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, About.class));
            }
        });

        findViewById(R.id.detedId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, ImageClassify.class));
            }
        });


        findViewById(R.id.logoutBtnId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.signOut();
                startActivity(new Intent(Dashboard.this, MainActivity.class));
            }
        });

    }
}