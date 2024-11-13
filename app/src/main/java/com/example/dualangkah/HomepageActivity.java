package com.example.dualangkah;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable Edge-to-Edge support (if required)
        EdgeToEdge.enable(this);

        // Set the content view for the homepage layout
        setContentView(R.layout.homepage);

        // Greeting based on the time of day
        setGreeting();

        // Display the current date
        displayCurrentDate();

        // Set up navigation buttons
        setUpNavigationButtons();
    }

    // Method to set the greeting based on the current time
    private void setGreeting() {
        // Reference to the greeting TextView
        TextView greetingTextView = findViewById(R.id.greetingTextView);

        // Get the current hour of the day
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // Determine the greeting message based on the time of day
        String greeting;
        if (hour >= 5 && hour < 12) {
            greeting = "Selamat Pagi";
        } else if (hour >= 12 && hour < 15) {
            greeting = "Selamat Siang";
        } else if (hour >= 15 && hour < 18) {
            greeting = "Selamat Sore";
        } else {
            greeting = "Selamat Malam";
        }

        // Set the greeting text in the TextView
        greetingTextView.setText(greeting);
    }

    // Method to display the current date in the TextView
    private void displayCurrentDate() {
        // Reference to the date TextView
        TextView dateTextView = findViewById(R.id.dateTextView);

        // Format the current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(Calendar.getInstance().getTime());

        // Display the formatted date in the TextView
        dateTextView.setText(currentDate);
    }

    // Method to set up the navigation buttons
    private void setUpNavigationButtons() {
        // Reference to the navigation buttons
        ImageButton homeBtn = findViewById(R.id.homebtn);
        ImageButton mapsBtn = findViewById(R.id.mapsbtn);
        ImageButton profileBtn = findViewById(R.id.profilebtn);

        // Navigation to Homepage (current page)
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomepageActivity.this, HomepageActivity.class);
            startActivity(intent);
        });

        // Navigation to MapPage
        mapsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomepageActivity.this, MappageActivity.class);
            startActivity(intent);
        });

        // Navigation to ProfilePage
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomepageActivity.this, ProfilepageActivity.class);
            startActivity(intent);
        });
    }
}
