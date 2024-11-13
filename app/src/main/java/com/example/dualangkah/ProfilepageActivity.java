package com.example.dualangkah;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ProfilepageActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private TextView locationTextView;

    // Registering the permission request launcher
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean isGranted) {
                    if (isGranted) {
                        getUserLocation();
                    } else {
                        Toast.makeText(ProfilepageActivity.this, "Izin lokasi diperlukan untuk menampilkan lokasi.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage); // Ensure this layout exists

        locationTextView = findViewById(R.id.locationTextView); // Ensure this ID exists in your profilepage layout
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Request location permission and get user location
        requestLocationPermission();

        // Fixing Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencing the date TextView and setting the current date
        TextView dateTextView = findViewById(R.id.dateTextView);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();  // Initialize Calendar instance
        String currentDate = dateFormat.format(calendar.getTime()); // Using the initialized Calendar object
        dateTextView.setText(currentDate); // Display current date
    }

    private void requestLocationPermission() {
        // Checking if we already have permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request permission if not granted
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            getUserLocation();  // Get location if permission is granted
        }
    }

    private void getUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        Geocoder geocoder = new Geocoder(ProfilepageActivity.this, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (addresses != null && !addresses.isEmpty()) {
                                Address address = addresses.get(0);
                                String city = address.getLocality();
                                String country = address.getCountryName();
                                locationTextView.setText(city + ", " + country); // Display location
                            } else {
                                locationTextView.setText("Lokasi tidak ditemukan");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            locationTextView.setText("Gagal mendapatkan lokasi");
                        }
                    }
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Handling button clicks
        Button logoutButton = findViewById(R.id.logoutbtn);
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilepageActivity.this, MainActivity.class);
            startActivity(intent); // Navigate to MainActivity
        });

        // Adding navigation for image buttons
        ImageButton homeBtn = findViewById(R.id.homebtn);
        ImageButton mapsBtn = findViewById(R.id.mapsbtn);
        ImageButton profileBtn = findViewById(R.id.profilebtn);

        // Navigate to ProfilePage (this page itself)
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilepageActivity.this, ProfilepageActivity.class);
            startActivity(intent);
        });

        // Navigate to MapPage
        mapsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilepageActivity.this, MappageActivity.class);
            startActivity(intent);
        });

        // Navigate to Homepage
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilepageActivity.this, HomepageActivity.class);
            startActivity(intent);
        });
    }
}
