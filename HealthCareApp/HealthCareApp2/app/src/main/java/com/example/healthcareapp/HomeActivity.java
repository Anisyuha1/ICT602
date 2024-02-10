package com.example.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up Toolbar as the support action bar
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Set up click listener for the About card
        CardView aboutCard = findViewById(R.id.cardAbout);
        aboutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on the "About" card
                Toast.makeText(HomeActivity.this, "Welcome to About Us", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        // Set up click listener for the Information card
        CardView informationCard = findViewById(R.id.cardInformation);
        informationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on the "Information" card
                Toast.makeText(HomeActivity.this, "Welcome to Information", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, Information.class);
                startActivity(intent);
            }
        });

        // Set up click listener for the Map card
        CardView mapCard = findViewById(R.id.cardMap);
        mapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on the "Map" card
                Toast.makeText(HomeActivity.this, "Opening Map", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        // Set up click listener for the HealthCare card
        CardView healthCareCard = findViewById(R.id.cardHealthCare);
        healthCareCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on the "HealthCare" card
                Toast.makeText(HomeActivity.this, "Welcome to HealthCare", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, Healthcare.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            if (item.getItemId() == R.id.cardHome) {
                Toast.makeText(this, "About", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true; // Return true to indicate that the event has been handled
            } else if (item.getItemId() == R.id.logout) {
                Toast.makeText(this, "Logout Successful!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the current activity
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
