package com.example.a2fa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class OtpActivity extends AppCompatActivity {

    private EditText inputFieldOtp;
    private Button validateOtpButton, resendOtpButton;
    private String recipient;
    private String generatedOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        inputFieldOtp = findViewById(R.id.editTextNumber);
        validateOtpButton = findViewById(R.id.buttonOtp);
        resendOtpButton = findViewById(R.id.buttonResend);

        recipient = getIntent().getStringExtra("recipient");
        if (recipient == null || recipient.isEmpty()) {
            Toast.makeText(this, "Recipient is missing!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        sendOtp();

        validateOtpButton.setOnClickListener(v -> {
            String userOtp = inputFieldOtp.getText().toString();
            if (userOtp.equals(generatedOtp)) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, com.example.a2fa.MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
            }
        });

        resendOtpButton.setOnClickListener(v -> sendOtp());
    }

    private void sendOtp() {
        generatedOtp = String.valueOf((int) (Math.random() * 900000) + 100000); // 6-shifror
        com.example.a2fa.EmailService.sendEmail(recipient, "Your OTP", "Your OTP is: " + generatedOtp);
        Toast.makeText(this, "OTP Sent", Toast.LENGTH_SHORT).show();
    }
}
