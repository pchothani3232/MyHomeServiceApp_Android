package com.example.myhomeserviceapp_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_category);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(Category.this, HomePage.class)); // Redirecting to HomePage
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_category) {
                return true; // Already on this page
            } else if (itemId == R.id.nav_my_service) {
                startActivity(new Intent(Category.this, SelectedServices.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });

        // Find Views for Category Clicks
        LinearLayout personalServices = findViewById(R.id.personal_services);
        LinearLayout appliances = findViewById(R.id.appliances);
        LinearLayout cleaningAndPestControl = findViewById(R.id.cleaning_pest_control); // Fixed ID
        LinearLayout homeRepairs = findViewById(R.id.home_repairs);

        // Set Click Listeners
        personalServices.setOnClickListener(v -> startActivity(new Intent(Category.this, PersonalServices.class)));
        appliances.setOnClickListener(v -> startActivity(new Intent(Category.this, Appliances.class)));
        cleaningAndPestControl.setOnClickListener(v -> startActivity(new Intent(Category.this, CleaningAndPestControl.class)));
        homeRepairs.setOnClickListener(v -> startActivity(new Intent(Category.this, HomeRepair.class)));

        // Click Listener for Extra Services
//        TextView extraServices = findViewById(R.id.extra_services);
//        extraServices.setOnClickListener(v -> {
//            Intent intent = new Intent(Category.this, ExtraServices.class);
//            startActivity(intent);
//        });
    }
}
