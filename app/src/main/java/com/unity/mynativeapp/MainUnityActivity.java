package com.unity.mynativeapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.company.product.OverrideUnityActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainUnityActivity extends OverrideUnityActivity {
    // Setup activity layout
    String watchModel;
    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        addFinishButton();
        watchModel = intent.getStringExtra("model");
        handleIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch(watchModel){
            case "1":
                mUnityPlayer.UnitySendMessage("ScriptObject", "WatchOneButtonClicked","");
                addModel1ControlsToUnityFrame();
                break;
            case "2":
                mUnityPlayer.UnitySendMessage("ScriptObject", "WatchTwoButtonClicked", "");
                addModel2ControlsToUnityFrame();
                break;
            case "3":
                mUnityPlayer.UnitySendMessage("ScriptObject", "WatchThreeButtonClicked", "");
                addModel3ControlsToUnityFrame();
                break;
        }
        // mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch1BandColor","1");
        // mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch2BandColor","1");
        // mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch3BandColor","1");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
        setIntent(intent);
    }

    void handleIntent(Intent intent) {
        if(intent == null || intent.getExtras() == null) return;

        if(intent.getExtras().containsKey("doQuit"))
            if(mUnityPlayer != null) {
                finish();
            }
    }

    @Override
    protected void showMainActivity(String setToColor) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("setColor", setToColor);
        startActivity(intent);
    }

    @Override public void onUnityPlayerUnloaded() {
        showMainActivity("");
    }
     public void addFinishButton(){

         FrameLayout layout = mUnityPlayer;
         Button myButton = new Button(this);
         myButton.setText("Finish");
         myButton.setX(850);
         myButton.setY(10);

         myButton.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 finish();
             }
         });
         layout.addView(myButton, 250, 150);
     }
    public void addModel1ControlsToUnityFrame() {
        FrameLayout layout = mUnityPlayer;

        {
            Button myButton = new Button(this);
            myButton.setBackgroundColor(Color.parseColor("#111010"));
            myButton.setX(10);
            myButton.setY(10);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch1BandColor","1");
                }
            });
            layout.addView(myButton, 150, 150);
        }

        {
            Button myButton = new Button(this);
            myButton.setBackgroundColor(Color.parseColor("#103890"));
            myButton.setX(10);
            myButton.setY(10 + 160);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch1BandColor","2");
                }
            });
            layout.addView(myButton, 150, 150);
        }

        {
            Button myButton = new Button(this);
            myButton.setBackgroundColor(Color.parseColor("#C2D5FF"));
            myButton.setX(10);
            myButton.setY(10 + 2*160);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch1BandColor","3");
                }
            });
            layout.addView(myButton, 150, 150);
        }
    }

    public void addModel2ControlsToUnityFrame() {
        FrameLayout layout = mUnityPlayer;

        {
            Button myButton = new Button(this);
            myButton.setBackgroundColor(Color.parseColor("#111010"));
            myButton.setX(10);
            myButton.setY(10);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch2BandColor","1");
                }
            });
            layout.addView(myButton, 150, 150);
        }

        {
            Button myButton = new Button(this);
            myButton.setBackgroundColor(Color.parseColor("#C2D5FF"));
            myButton.setX(10);
            myButton.setY(10 + 160);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch2BandColor","3");
                }
            });
            layout.addView(myButton, 150, 150);
        }

        {
            Button myButton = new Button(this);
            myButton.setBackgroundColor(Color.parseColor("#740202"));
            myButton.setX(10);
            myButton.setY(10 + 2*160);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch2BandColor","4");
                }
            });
            layout.addView(myButton, 150, 150);
        }
    }

    public void addModel3ControlsToUnityFrame() {
        FrameLayout layout = mUnityPlayer;

        {
            Button myButton = new Button(this);
            myButton.setBackgroundColor(Color.parseColor("#111010"));
            myButton.setX(10);
            myButton.setY(10);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch3BandColor","1");
                }
            });
            layout.addView(myButton, 150, 150);
        }

        {
            Button myButton = new Button(this);
            myButton.setBackgroundColor(Color.parseColor("#7D7D7D"));
            myButton.setX(10);
            myButton.setY(10 + 160);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch3BandColor","3");
                }
            });
            layout.addView(myButton, 150, 150);
        }

        {
            Button myButton = new Button(this);
            myButton.setBackgroundColor(Color.parseColor("#98AAE7"));
            myButton.setX(10);
            myButton.setY(10 + 2*160);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mUnityPlayer.UnitySendMessage("ScriptObject", "setWatch3BandColor","2");
                }
            });
            layout.addView(myButton, 150, 150);
        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
