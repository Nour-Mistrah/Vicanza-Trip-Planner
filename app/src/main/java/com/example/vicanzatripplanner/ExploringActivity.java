package com.example.vicanzatripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.vicanzatripplanner.ui.home.Hotel;
import com.example.vicanzatripplanner.ui.home.HotelAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

import com.example.vicanzatripplanner.databinding.ActivityExploring2Binding;


public class ExploringActivity extends AppCompatActivity {

    private HotelAdapter hotelAdapter;
    private ActivityExploring2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Inflate the binding
        binding = ActivityExploring2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup the BottomNavigationView
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Define AppBarConfiguration (but don't include top-level destinations to enforce back button)
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder()
                .build(); // No top-level destinations

        // Setup NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_exploring);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Always enable the back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Button proceedButton = findViewById(R.id.proceedButton);
        proceedButton.setOnClickListener(v -> {
            List<Hotel> selectedHotels = hotelAdapter.getSelectedhotels();

            if (selectedHotels.isEmpty()) {
                Toast.makeText(this, "No hotels selected!", Toast.LENGTH_SHORT).show();
                return;

            }

            // Convert list to ArrayList of strings for passing via Intent
            ArrayList<String> hotelNames = new ArrayList<>();
            ArrayList<String> hotelLocations = new ArrayList<>();
            ArrayList<String> hotelPrices = new ArrayList<>();
            ArrayList<String> hotelImages = new ArrayList<>();

            for (Hotel hotel : selectedHotels) {
                hotelNames.add(hotel.getName());
                hotelLocations.add(hotel.getLocation());
                hotelPrices.add(String.valueOf(hotel.getPrice()));
                hotelImages.add(hotel.getimage_url());
            }

            // Pass selected items to ReservationActivity
            Intent intent = new Intent(this, ReservationActivity.class);
            intent.putStringArrayListExtra("hotelNames", hotelNames);
            intent.putStringArrayListExtra("hotelLocations", hotelLocations);
            intent.putStringArrayListExtra("hotelPrices", hotelPrices);
            intent.putStringArrayListExtra("hotelImages", hotelImages);
            startActivity(intent);
        });
    }


    private void setSupportActionBar(Toolbar toolbar) {
    }


    public void ReservationButtonHandler(View v){
        Intent i=new Intent(ExploringActivity.this,ReservationActivity.class);
        startActivity(i);
    }

}