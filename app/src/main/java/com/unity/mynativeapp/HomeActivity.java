package com.unity.mynativeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        System.out.println(currentUser);
    }

    @Override
    public void onBackPressed() {
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        finish();
    }

    public void goToWatchModel1(View view){
        startActivity(new Intent(getApplicationContext(), ModelAActivity.class));
    }

    public void goToWatchModel2(View view){
        startActivity(new Intent(getApplicationContext(), ModelBActivity.class));
    }

    public void goToWatchModel3(View view){
        startActivity(new Intent(getApplicationContext(), ModelCActivity.class));
    }
}