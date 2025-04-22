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

public class Login extends AppCompatActivity {

    private EditText nameInput, passwordInput;
    private Button loginButton;
    private TextView signUpText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Views
        nameInput     = findViewById(R.id.username);  // username input field
        passwordInput = findViewById(R.id.password);  // password input field
        loginButton   = findViewById(R.id.loginButton);
        signUpText    = findViewById(R.id.signUpText);

        // Styled text for sign-up link
        String linkText = "Don't have an account? <font color='#FFEB3B'><b>SIGN UP</b></font>";
        signUpText.setText(android.text.Html.fromHtml(linkText));

        // SharedPreferences to get the saved name and password
        sharedPreferences = getSharedPreferences("UserDB", MODE_PRIVATE);

        // Set button listeners
        loginButton.setOnClickListener(v -> attemptLogin());
        signUpText.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            finish();
        });
    }

    private void attemptLogin() {
        String name     = nameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(name)) {
            nameInput.setError("Enter your name");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Enter your password");
            return;
        }

        // Retrieve stored credentials from SharedPreferences
        String storedName     = sharedPreferences.getString("name", "");
        String storedPassword = sharedPreferences.getString("password", "");

        // Check if no account exists yet
        if (storedName.isEmpty()) {
            Toast.makeText(this, "No account found. Please sign up first.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate the entered name and password
        if (name.equals(storedName) && password.equals(storedPassword)) {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomePage.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        } else {
            Toast.makeText(this, "Invalid Name or Password", Toast.LENGTH_SHORT).show();
            passwordInput.setText("");
        }
    }
}
