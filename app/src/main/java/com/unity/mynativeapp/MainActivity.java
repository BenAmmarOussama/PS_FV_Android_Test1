package com.unity.mynativeapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean isUnityLoaded = false;
    private Button btnModel1, btnModel2, btnModel3, btnLogout;
    private TextView textViewUserName;

    private FirebaseUser user;
    private DatabaseReference ref;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("users");
        userId = user.getUid();

        btnModel1 = (Button) findViewById(R.id.btnModel1);
        btnModel1.setOnClickListener(this);
        btnModel2 = (Button) findViewById(R.id.btnModel2);
        btnModel2.setOnClickListener(this);
        btnModel3 = (Button) findViewById(R.id.btnModel3);
        btnModel3.setOnClickListener(this);
        btnLogout = (Button) findViewById(R.id.btnLogOut);
        btnLogout.setOnClickListener(this);
        textViewUserName = (TextView) findViewById(R.id.textViewUserName);

        //handleIntent(getIntent());

        ref.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String name = userProfile.Name;
                    String age = userProfile.age;
                    String email = userProfile.email;

                    textViewUserName.setText(name);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Oops 1 !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Oops 2 !", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
        setIntent(intent);
    }

    void handleIntent(Intent intent) {
        if(intent == null || intent.getExtras() == null) return;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) isUnityLoaded = false;
    }

    public void unloadUnity(Boolean doShowToast) {
        if(isUnityLoaded) {
            Intent intent = new Intent(this, MainUnityActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.putExtra("doQuit", true);
            startActivity(intent);
            isUnityLoaded = false;
        }
        else if(doShowToast) showToast("Show Unity First");
    }

    public void btnUnloadUnity(View v) {
        unloadUnity(true);
    }

    public void showToast(String message) {
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(view.getContext(), MainUnityActivity.class);;
        switch (view.getId()){
            case ( R.id.btnModel1):
                i.putExtra("model", "model1");
                startActivity(i);
                break;
            case (R.id.btnModel2):
                i.putExtra("model", "model2");
                startActivity(i);
                break;
            case (R.id.btnModel3):
                i.putExtra("model", "model3");
                startActivity(i);
                break;
            case R.id.btnLogOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

    }
}
