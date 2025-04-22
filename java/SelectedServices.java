package com.example.myhomeserviceapp_android;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class SelectedServices extends AppCompatActivity {

    private EditText datePicker, timePicker;
    private String selectedDate = "", selectedTime = "", repeatOption = "Once";
    private RadioGroup repeatOptions;
    private TextView grandTotalTextView;
    private Button proceedButton;
    private String selectedServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_services);

        // Initialize Views
        TextView serviceListTextView = findViewById(R.id.service_list);
        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        repeatOptions = findViewById(R.id.repeat_options);
        proceedButton = findViewById(R.id.proceed_button);
        grandTotalTextView = findViewById(R.id.grand_total);

        proceedButton.setEnabled(false);

        // Display Grand Total
        int totalPrice = getIntent().getIntExtra("totalPrice", 0);
        grandTotalTextView.setText(String.format("Grand Total: $%d", totalPrice));

        // Display selected services
        selectedServices = getSelectedServices();
        serviceListTextView.setText(selectedServices);

        if (selectedServices.equals("No services selected.")) {
            Toast.makeText(this, "Please select at least one service to continue!", Toast.LENGTH_LONG).show();
        }

        // Date Picker
        datePicker.setOnClickListener(v -> showDatePicker());

        // Time Picker
        timePicker.setOnClickListener(v -> showTimePicker());

        // Repeat Options
        repeatOptions.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.repeat_daily) repeatOption = "Daily";
            else if (checkedId == R.id.repeat_weekly) repeatOption = "Weekly";
            else repeatOption = "Once";
        });

        // Proceed Button
        proceedButton.setOnClickListener(v -> {
            if (selectedServices.equals("No services selected.")) {
                Toast.makeText(this, "No service selected. Please go back and choose at least one.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedDate.isEmpty() || selectedTime.isEmpty()) {
                Toast.makeText(this, "Please select both Date and Time!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SelectedServices.this, ConfirmationPage.class);
                intent.putExtra("selectedServices", selectedServices);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("selectedTime", selectedTime);
                intent.putExtra("repeatOption", repeatOption);
                intent.putExtra("grandTotal", grandTotalTextView.getText().toString());
                startActivity(intent);
            }
        });

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_my_service);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, HomePage.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_category) {
                startActivity(new Intent(this, Category.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_my_service) {
                return true;
            }
            return false;
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    selectedDate = dayOfMonth + "-" + (month + 1) + "-" + year;
                    datePicker.setText(selectedDate);
                    checkProceedButtonState();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    private void showTimePicker() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, selectedMinute) -> {
                    Calendar selectedCal = Calendar.getInstance();
                    selectedCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedCal.set(Calendar.MINUTE, selectedMinute);

                    if (isTodaySelected() && selectedCal.before(now)) {
                        Toast.makeText(this, "Please select a future time.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                    selectedTime = sdf.format(selectedCal.getTime());
                    timePicker.setText(selectedTime);
                    checkProceedButtonState();
                },
                hour, minute, false);

        timePickerDialog.show();
    }

    private boolean isTodaySelected() {
        if (selectedDate.isEmpty()) return false;

        Calendar today = Calendar.getInstance();
        Calendar selected = Calendar.getInstance();

        try {
            String[] parts = selectedDate.split("-");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]) - 1;
            int year = Integer.parseInt(parts[2]);
            selected.set(year, month, day);

            return today.get(Calendar.YEAR) == selected.get(Calendar.YEAR)
                    && today.get(Calendar.DAY_OF_YEAR) == selected.get(Calendar.DAY_OF_YEAR);
        } catch (Exception e) {
            return false;
        }
    }

    private void checkProceedButtonState() {
        boolean hasDateTime = !selectedDate.isEmpty() && !selectedTime.isEmpty();
        boolean hasServices = !selectedServices.equals("No services selected.");
        proceedButton.setEnabled(hasDateTime && hasServices);
    }

    private String getSelectedServices() {
        Map<String, Integer> services = new LinkedHashMap<>();
        services.put("Home Salon", getIntent().getIntExtra("salonCount", 0));
        services.put("Therapy", getIntent().getIntExtra("therapyCount", 0));
        services.put("Pet Care", getIntent().getIntExtra("petCount", 0));
        services.put("Yoga Trainer", getIntent().getIntExtra("yogaCount", 0));
        services.put("Plumbing", getIntent().getIntExtra("plumbingCount", 0));
        services.put("Electrical", getIntent().getIntExtra("electricalCount", 0));
        services.put("Carpenter", getIntent().getIntExtra("carpenterCount", 0));
        services.put("Painting", getIntent().getIntExtra("paintingCount", 0));
        services.put("AC Repair", getIntent().getIntExtra("acCount", 0));
        services.put("Fridge Repair", getIntent().getIntExtra("fridgeCount", 0));
        services.put("Microwave Repair", getIntent().getIntExtra("microwaveCount", 0));
        services.put("Geyser Repair", getIntent().getIntExtra("geyserCount", 0));
        services.put("Deep Home Cleaning", getIntent().getIntExtra("deepHomeCount", 0));
        services.put("Bathroom Cleaning", getIntent().getIntExtra("bathroomCount", 0));
        services.put("Sofa & Carpet Cleaning", getIntent().getIntExtra("sofaCarpetCount", 0));
        services.put("Kitchen Cleaning", getIntent().getIntExtra("kitchenCount", 0));

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : services.entrySet()) {
            if (entry.getValue() > 0) {
                builder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        }

        return builder.length() > 0 ? builder.toString().trim() : "No services selected.";
    }
}
