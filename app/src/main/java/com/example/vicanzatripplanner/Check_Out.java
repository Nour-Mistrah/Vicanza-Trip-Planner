package com.example.vicanzatripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ProgressBar;

public class Check_Out extends AppCompatActivity {

   Button confirmPaymentButton;
  ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_check_out);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressBar = findViewById(R.id.progressBar);
        confirmPaymentButton= findViewById(R.id.Paymentbutton);


        confirmPaymentButton.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);

            new Handler().postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Check_Out.this, "Payment Successful!", Toast.LENGTH_SHORT).show();

                // Redirect to booking confirmation
                Intent intent = new Intent(Check_Out.this, Schedule.class);
                startActivity(intent);
                finish();
            }, 3000); // 3-second delay to simulate processing
        });

    }


}