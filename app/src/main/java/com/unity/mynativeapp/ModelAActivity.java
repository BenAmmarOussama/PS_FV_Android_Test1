package com.unity.mynativeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModelAActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnShowUnity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_a);

        btnShowUnity = (Button) findViewById(R.id.btnShowUnity);
        btnShowUnity.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btnShowUnity):
                Intent unityIntent = new Intent(this, MainUnityActivity.class);
                unityIntent.putExtra("model", "1");
                startActivity(unityIntent);
        }
    }
}