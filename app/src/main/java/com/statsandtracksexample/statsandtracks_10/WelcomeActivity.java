package com.statsandtracksexample.statsandtracks_10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button btn;
    Button statsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn = (Button) findViewById(R.id.toMapButton);

        statsBtn = (Button) findViewById(R.id.toStatsButton);

        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toStatsActivity();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
    }

    public void nextActivity(){
        Intent nextIntent = new Intent(this, IntegratingMapActivity.class);
        String message = "sample";
        nextIntent.putExtra("USERNAME", message);
        startActivity(nextIntent);
    }

    public void toStatsActivity(){
        Intent nextIntent = new Intent(this, SpeedStatsActivity.class);
        startActivity(nextIntent);
    }
}
