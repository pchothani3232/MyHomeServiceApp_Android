package com.example.myhomeserviceapp_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HomeRepair extends AppCompatActivity {

    // Service counts
    private int plumbingCount = 0;
    private int electricalCount = 0;
    private int carpenterCount = 0;
    private int paintingCount = 0;

    // TextViews
    private TextView plumbingCountView, electricalCountView, carpenterCountView, paintingCountView, totalPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_repair);

        // Initialize TextViews
        plumbingCountView = findViewById(R.id.salon_count);
        electricalCountView = findViewById(R.id.therapy_count);
        carpenterCountView = findViewById(R.id.pet_count);
        paintingCountView = findViewById(R.id.yoga_count);
        totalPriceView = findViewById(R.id.total_price);

        // Plumbing
        findViewById(R.id.increase_salon).setOnClickListener(v -> updateCount(plumbingCountView, ++plumbingCount));
        findViewById(R.id.decrease_salon).setOnClickListener(v -> {
            if (plumbingCount > 0) updateCount(plumbingCountView, --plumbingCount);
        });

        // Electrical
        findViewById(R.id.increase_therapy).setOnClickListener(v -> updateCount(electricalCountView, ++electricalCount));
        findViewById(R.id.decrease_therapy).setOnClickListener(v -> {
            if (electricalCount > 0) updateCount(electricalCountView, --electricalCount);
        });

        // Carpenter
        findViewById(R.id.increase_pet).setOnClickListener(v -> updateCount(carpenterCountView, ++carpenterCount));
        findViewById(R.id.decrease_pet).setOnClickListener(v -> {
            if (carpenterCount > 0) updateCount(carpenterCountView, --carpenterCount);
        });

        // Painting
        findViewById(R.id.increase_yoga).setOnClickListener(v -> updateCount(paintingCountView, ++paintingCount));
        findViewById(R.id.decrease_yoga).setOnClickListener(v -> {
            if (paintingCount > 0) updateCount(paintingCountView, --paintingCount);
        });

        // Next Button
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeRepair.this, SelectedServices.class);
            intent.putExtra("plumbingCount", plumbingCount);
            intent.putExtra("electricalCount", electricalCount);
            intent.putExtra("carpenterCount", carpenterCount);
            intent.putExtra("paintingCount", paintingCount);
            intent.putExtra("totalPrice", calculateTotalPrice());
            startActivity(intent);
        });

        // Initialize total price display
        updateTotalPrice();
    }

    // Update individual count and total price
    private void updateCount(TextView view, int count) {
        view.setText(String.valueOf(count));
        updateTotalPrice();
    }

    // Calculate and display total price
    private void updateTotalPrice() {
        int total = calculateTotalPrice();
        totalPriceView.setText("Total: $" + total);
    }

    // Pricing logic
    private int calculateTotalPrice() {
        return (plumbingCount * 25) + (electricalCount * 30) + (carpenterCount * 35) + (paintingCount * 40);
    }
}
