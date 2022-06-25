package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpeningActivity extends AppCompatActivity {
    private Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        getStartedButton = findViewById(R.id.startButton);

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this is how we go to login
                startActivity(new Intent(OpeningActivity.this, LoginActivity.class));
            }
        });

    }
}