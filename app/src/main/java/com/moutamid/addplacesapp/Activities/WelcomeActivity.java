package com.moutamid.addplacesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moutamid.addplacesapp.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting the content view to the welcome activity layout
        setContentView(R.layout.activity_welcome);
        // Finding the button by its ID from the layout
        Button b1 = findViewById(R.id.startbtn);
        // Setting an onClick listener on the button
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating an intent to start the LoginActivity
                Intent i = new Intent(WelcomeActivity.this , LoginActivity.class);
                startActivity(i);
            }
        });
    }
}