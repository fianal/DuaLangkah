package com.example.dualangkah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); // Pastikan Anda memuat layout yang sesuai untuk MainActivity2

        // Mengatur padding untuk sistem bar (misalnya, status bar dan navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Menginisialisasi TextView dengan ID blmpnyakn dan menambahkan OnClickListener
        TextView textViewToMainActivity = findViewById(R.id.blmpnyakn);
        textViewToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat intent untuk berpindah ke MainActivity
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Menambahkan OnClickListener untuk tombol login yang mengarah ke HomeActivity
        Button loginButton = findViewById(R.id.loginbtn2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat intent untuk berpindah ke HomeActivity
                Intent intent = new Intent(MainActivity2.this, HomepageActivity.class);
                startActivity(intent);
            }
        });
    }
}
