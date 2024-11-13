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

public class MappageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mappage);

        // Referensi ke TextView dateTextView
        TextView dateTextView = findViewById(R.id.dateTextView);

        // Format tanggal, bulan, dan tahun
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        String currentDate = dateFormat.format(calendar.getTime());

        // Tampilkan tanggal di TextView
        dateTextView.setText(currentDate);

        // Tambahkan navigasi ke ImageButton
        ImageButton homeBtn = findViewById(R.id.homebtn);
        ImageButton mapsBtn = findViewById(R.id.mapsbtn);
        ImageButton profileBtn = findViewById(R.id.profilebtn);

        // Tombol navigasi ke halaman HomePage
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MappageActivity.this, HomepageActivity.class);
            startActivity(intent);
        });

        // Tombol navigasi ke halaman MapPage (halaman ini sendiri)
        mapsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MappageActivity.this, MappageActivity.class);
            startActivity(intent);
        });

        // Tombol navigasi ke halaman ProfilePage
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MappageActivity.this, ProfilepageActivity.class);
            startActivity(intent);
        });
    }
}
