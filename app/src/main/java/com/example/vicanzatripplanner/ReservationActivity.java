package com.example.vicanzatripplanner;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.vicanzatripplanner.ui.home.Hotel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class ReservationActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private SelectedHotelAdapter adapter;
    private List<Hotel> selectedHotels = new ArrayList<>();
    TextView packagePrice;
    int packageId;
    TextView packageName,packageLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reservation2);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve data from intent
        ArrayList<String> hotelNames = getIntent().getStringArrayListExtra("hotelNames");
        ArrayList<String> hotelLocations = getIntent().getStringArrayListExtra("hotelLocations");
        ArrayList<String> hotelPrices = getIntent().getStringArrayListExtra("hotelPrices");
        ArrayList<String> hotelImages = getIntent().getStringArrayListExtra("hotelImages");

        if (hotelNames != null) {
            for (int i = 0; i < hotelNames.size(); i++) {
                selectedHotels.add(new Hotel( i, hotelNames.get(i), hotelLocations.get(i),
                        Double.parseDouble(hotelPrices.get(i)), hotelImages.get(i)));
            }
        }

        // Set adapter
        adapter = new SelectedHotelAdapter(this, selectedHotels);
        recyclerView.setAdapter(adapter);





            packagePrice = findViewById(R.id.totalPriceText);


            packageId = getIntent().getIntExtra("Packageid", -1); // Get ID from Intent
            if (packageId != -1) {
                fetchPackageDetails(packageId); // Fetch package details
            } else {
                Toast.makeText(this, "No package selected", Toast.LENGTH_SHORT).show();
            }

    }





    public void CheckOutButtonHandler(View v){
        Intent intent =new Intent(ReservationActivity.this,Check_Out.class);
        startActivity(intent);
    }

    private void fetchPackageDetails(int packageId) {
        String url = "http://10.0.2.2/android_api/getPackagesDetails.php?package_id=" + packageId;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.has("error")) {
                            Toast.makeText(this, "Package not found", Toast.LENGTH_SHORT).show();
                        } else {

                            packagePrice.setText(jsonObject.getString("Packageprice"));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
        );

        queue.add(stringRequest);
    }
}
