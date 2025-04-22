package com.example.myhomeserviceapp_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalServices extends AppCompatActivity {

    // Service counts
    private int salonCount = 0;
    private int therapyCount = 0;
    private int petCount = 0;
    private int yogaCount = 0;

    // TextViews
    private TextView salonCountView, therapyCountView, petCountView, yogaCountView, totalPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_services);

        // Initialize views
        salonCountView = findViewById(R.id.salon_count);
        therapyCountView = findViewById(R.id.therapy_count);
        petCountView = findViewById(R.id.pet_count);
        yogaCountView = findViewById(R.id.yoga_count);
        totalPriceView = findViewById(R.id.total_price);

        // Home Salon
        findViewById(R.id.increase_salon).setOnClickListener(v -> updateCount(salonCountView, ++salonCount));
        findViewById(R.id.decrease_salon).setOnClickListener(v -> {
            if (salonCount > 0) updateCount(salonCountView, --salonCount);
        });

        // Therapy
        findViewById(R.id.increase_therapy).setOnClickListener(v -> updateCount(therapyCountView, ++therapyCount));
        findViewById(R.id.decrease_therapy).setOnClickListener(v -> {
            if (therapyCount > 0) updateCount(therapyCountView, --therapyCount);
        });

        // Pet Care
        findViewById(R.id.increase_pet).setOnClickListener(v -> updateCount(petCountView, ++petCount));
        findViewById(R.id.decrease_pet).setOnClickListener(v -> {
            if (petCount > 0) updateCount(petCountView, --petCount);
        });

        // Yoga Trainer
        findViewById(R.id.increase_yoga).setOnClickListener(v -> updateCount(yogaCountView, ++yogaCount));
        findViewById(R.id.decrease_yoga).setOnClickListener(v -> {
            if (yogaCount > 0) updateCount(yogaCountView, --yogaCount);
        });

        // Next Button
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(PersonalServices.this, SelectedServices.class);
            intent.putExtra("salonCount", salonCount);
            intent.putExtra("therapyCount", therapyCount);
            intent.putExtra("petCount", petCount);
            intent.putExtra("yogaCount", yogaCount);
            intent.putExtra("totalPrice", calculateTotalPrice());
            startActivity(intent);
        });

        // Initialize total
        updateTotalPrice();
    }

    private void updateCount(TextView view, int count) {
        view.setText(String.valueOf(count));
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        int total = calculateTotalPrice();
        totalPriceView.setText("Total: $" + total);
    }

    private int calculateTotalPrice() {
        return (salonCount * 25) + (therapyCount * 50) + (petCount * 30) + (yogaCount * 5);
    }
}
