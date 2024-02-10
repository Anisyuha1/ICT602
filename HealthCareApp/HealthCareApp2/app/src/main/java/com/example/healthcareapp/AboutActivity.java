package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    Toolbar aboutToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Set up Toolbar as the support action bar
        aboutToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(aboutToolbar);
        getSupportActionBar().setTitle("About Us");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            if (item.getItemId() == R.id.homepage) {
                Toast.makeText(this, "Welcome to home page", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, HomeActivity.class);
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