package com.example.vicanzatripplanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {


    ImageButton goExplore, goPackages;

    EditText Date, Duration;
    Button SaveBtn;
    String URL = "http://10.0.2.2/android_api/SaveTripData.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);








        goPackages = findViewById(R.id.goPackages);







        goPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SecondActivity.this, PackagesActivity.class);
                startActivity(i);


            }
        });

        Date = findViewById(R.id.date);
        Duration = findViewById(R.id.duration);

        SaveBtn = findViewById(R.id.SaveBtn);

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Savedata();
            }
        });


    }

    private void Savedata() {
        final String tripDate = Date.getText().toString().trim();
        final String tripDuration = Duration.getText().toString().trim();

        android.util.Log.d("TripSaveDebug", "Request URL: " + URL);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SecondActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        android.util.Log.e("TripSaveError", "Failed to save trip", error);
                        if (error.networkResponse != null) {
                            android.util.Log.e(
                                    "TripSaveError",
                                    "HTTP status: " + error.networkResponse.statusCode
                            );

                            if (error.networkResponse.data != null) {
                                android.util.Log.e(
                                        "TripSaveError",
                                        "Server response: " +
                                                new String(error.networkResponse.data)
                                );
                            }
                        }
                        Toast.makeText(SecondActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("date", tripDate);
                params.put("duration", tripDuration);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
