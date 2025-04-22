package com.example.myhomeserviceapp_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationPage extends AppCompatActivity {

    private TextView serviceTextView, dateTextView, timeTextView, repeatTextView, totalTextView;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);

        // Initialize views
        serviceTextView = findViewById(R.id.service_details);
        dateTextView = findViewById(R.id.date_details);
        timeTextView = findViewById(R.id.time_details);
        repeatTextView = findViewById(R.id.repeat_details);
        totalTextView = findViewById(R.id.total_details);
        doneButton = findViewById(R.id.btn_done);

        // Retrieve data from Intent
        Intent intent = getIntent();
        String selectedServices = intent.getStringExtra("selectedServices");
        String selectedDate = intent.getStringExtra("selectedDate");
        String selectedTime = intent.getStringExtra("selectedTime");
        String repeatOption = intent.getStringExtra("repeatOption");
        String grandTotal = intent.getStringExtra("grandTotal");

        // Set data to views with fallbacks
        serviceTextView.setText("Selected Services:\n" + (selectedServices != null ? selectedServices : "None"));
        dateTextView.setText("" + (selectedDate != null ? selectedDate : "Not set"));
        timeTextView.setText("" + (selectedTime != null ? selectedTime : "Not set"));
        repeatTextView.setText("" + (repeatOption != null ? repeatOption : "Once"));
        totalTextView.setText("$" + (grandTotal != null ? grandTotal.replace("Grand Total: $", "") : "0"));

        // Done button action
        doneButton.setOnClickListener(v -> {
            // You can redirect to MainActivity or Home
            Intent homeIntent = new Intent(ConfirmationPage.this, HomePage.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
            finish();
        });
    }
}
