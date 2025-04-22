package com.example.myhomeserviceapp_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CleaningAndPestControl extends AppCompatActivity {

    // Service counts
    private int deepHomeCount = 0;
    private int bathroomCount = 0;
    private int sofaCarpetCount = 0;
    private int kitchenCount = 0;

    // TextViews
    private TextView deepHomeCountView, bathroomCountView, sofaCarpetCountView, kitchenCountView, totalPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cleaning_and_pest_control);

        // Initialize views
        deepHomeCountView = findViewById(R.id.salon_count);
        bathroomCountView = findViewById(R.id.therapy_count);
        sofaCarpetCountView = findViewById(R.id.pet_count);
        kitchenCountView = findViewById(R.id.yoga_count);
        totalPriceView = findViewById(R.id.total_price);

        // Deep Home Cleaning
        findViewById(R.id.increase_salon).setOnClickListener(v -> updateCount(deepHomeCountView, ++deepHomeCount));
        findViewById(R.id.decrease_salon).setOnClickListener(v -> {
            if (deepHomeCount > 0) updateCount(deepHomeCountView, --deepHomeCount);
        });

        // Bathroom Cleaning
        findViewById(R.id.increase_therapy).setOnClickListener(v -> updateCount(bathroomCountView, ++bathroomCount));
        findViewById(R.id.decrease_therapy).setOnClickListener(v -> {
            if (bathroomCount > 0) updateCount(bathroomCountView, --bathroomCount);
        });

        // Sofa & Carpet Cleaning
        findViewById(R.id.increase_pet).setOnClickListener(v -> updateCount(sofaCarpetCountView, ++sofaCarpetCount));
        findViewById(R.id.decrease_pet).setOnClickListener(v -> {
            if (sofaCarpetCount > 0) updateCount(sofaCarpetCountView, --sofaCarpetCount);
        });

        // Kitchen Cleaning
        findViewById(R.id.increase_yoga).setOnClickListener(v -> updateCount(kitchenCountView, ++kitchenCount));
        findViewById(R.id.decrease_yoga).setOnClickListener(v -> {
            if (kitchenCount > 0) updateCount(kitchenCountView, --kitchenCount);
        });

        // Next Button
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(CleaningAndPestControl.this, SelectedServices.class);
            intent.putExtra("deepHomeCount", deepHomeCount);
            intent.putExtra("bathroomCount", bathroomCount);
            intent.putExtra("sofaCarpetCount", sofaCarpetCount);
            intent.putExtra("kitchenCount", kitchenCount);
            intent.putExtra("totalPrice", calculateTotalPrice());
            startActivity(intent);
        });

        // Initialize total price
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
        return (deepHomeCount * 60) + (bathroomCount * 40) + (sofaCarpetCount * 50) + (kitchenCount * 50);
    }
}
