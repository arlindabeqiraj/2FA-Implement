package com.example.a2fa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity loaded successfully.");

        // Find the logout button
        Button logoutButton = findViewById(R.id.buttonLogout);

        // Set up logout action
        logoutButton.setOnClickListener(v -> {
            // Navigate back to LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            // Clear activity stack to prevent going back to MainActivity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish(); // Close current activity
        });
    }
}
