package com.example.myhomeserviceapp_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameInput, passwordInput, confirmPasswordInput;
    private Button signupButton;
    private TextView loginText;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Views
        nameInput         = findViewById(R.id.nameInput);
        passwordInput     = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        signupButton      = findViewById(R.id.signupButton);
        loginText         = findViewById(R.id.loginText);

        // Styled text for login link
        String styledText = "Already have an account? <font color='#FFEB3B'><b>LOGIN</b></font>";
        loginText.setText(android.text.Html.fromHtml(styledText));

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserDB", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Set up button listeners
        signupButton.setOnClickListener(v -> registerUser());
        loginText.setOnClickListener(v -> {
            startActivity(new Intent(this, Login.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            finish();
        });
    }

    private void registerUser() {
        String name     = nameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        // Validate name
        if (TextUtils.isEmpty(name)) {
            nameInput.setError("Enter your name");
            return;
        }
        if (!name.matches("[a-zA-Z\\s]+")) {
            nameInput.setError("Name can only contain letters and spaces");
            return;
        }

        // Validate password
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordInput.setError("Password must be at least 6 characters");
            return;
        }

        // Confirm password match
        if (!password.equals(confirmPassword)) {
            confirmPasswordInput.setError("Passwords do not match");
            return;
        }

        // Save data in SharedPreferences
        editor.putString("name", name);
        editor.putString("password", password);
        editor.apply();

        // Display success message
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();

        // Redirect to login screen
        startActivity(new Intent(this, Login.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }
}
