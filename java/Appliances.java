
package com.example.myhomeserviceapp_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Appliances extends AppCompatActivity {

    // Counters
    private int acCount = 0;
    private int fridgeCount = 0;
    private int microwaveCount = 0;
    private int geyserCount = 0;

    // Count Views
    private TextView acCountView, fridgeCountView, microwaveCountView, geyserCountView, totalPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_appliances);

        // Link count TextViews
        acCountView = findViewById(R.id.salon_count);
        fridgeCountView = findViewById(R.id.therapy_count);
        microwaveCountView = findViewById(R.id.pet_count);
        geyserCountView = findViewById(R.id.yoga_count);
        totalPriceView = findViewById(R.id.total_price); // Add this TextView in your XML

        // AC Repair buttons
        Button increaseAC = findViewById(R.id.increase_salon);
        Button decreaseAC = findViewById(R.id.decrease_salon);
        increaseAC.setOnClickListener(v -> updateCount(acCountView, ++acCount));
        decreaseAC.setOnClickListener(v -> {
            if (acCount > 0) updateCount(acCountView, --acCount);
        });

        // Fridge Repair buttons
        Button increaseFridge = findViewById(R.id.increase_therapy);
        Button decreaseFridge = findViewById(R.id.decrease_therapy);
        increaseFridge.setOnClickListener(v -> updateCount(fridgeCountView, ++fridgeCount));
        decreaseFridge.setOnClickListener(v -> {
            if (fridgeCount > 0) updateCount(fridgeCountView, --fridgeCount);
        });

        // Microwave Repair buttons
        Button increaseMicrowave = findViewById(R.id.increase_pet);
        Button decreaseMicrowave = findViewById(R.id.decrease_pet);
        increaseMicrowave.setOnClickListener(v -> updateCount(microwaveCountView, ++microwaveCount));
        decreaseMicrowave.setOnClickListener(v -> {
            if (microwaveCount > 0) updateCount(microwaveCountView, --microwaveCount);
        });

        // Geyser Repair buttons
        Button increaseGeyser = findViewById(R.id.increase_yoga);
        Button decreaseGeyser = findViewById(R.id.decrease_yoga);
        increaseGeyser.setOnClickListener(v -> updateCount(geyserCountView, ++geyserCount));
        decreaseGeyser.setOnClickListener(v -> {
            if (geyserCount > 0) updateCount(geyserCountView, --geyserCount);
        });

        // Next Button
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(Appliances.this, SelectedServices.class);
            intent.putExtra("acCount", acCount);
            intent.putExtra("fridgeCount", fridgeCount);
            intent.putExtra("microwaveCount", microwaveCount);
            intent.putExtra("geyserCount", geyserCount);
            intent.putExtra("totalPrice", calculateTotalPrice());
            startActivity(intent);
        });

        // Set initial price
        updateTotalPrice();
    }

    // Updates the count and total price
    private void updateCount(TextView view, int count) {
        view.setText(String.valueOf(count));
        updateTotalPrice();
    }

    // Calculate total price
    private int calculateTotalPrice() {
        return (acCount * 49) + (fridgeCount * 69) + (microwaveCount * 39) + (geyserCount * 59);
    }

    // Show updated price
    private void updateTotalPrice() {
        int total = calculateTotalPrice();
        totalPriceView.setText("Total: $" + total);
    }
}
