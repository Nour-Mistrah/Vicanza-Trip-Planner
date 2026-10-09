package com.example.vicanzatripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Schedule extends AppCompatActivity {
    TextView textView;

    String url = "http://10.0.2.2/android_api/getTripDetails.php"; // Change this to your actual server IP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule); // Change to your actual layout

        textView = findViewById(R.id.textView); // Change to your actual TextView ID
        fetchData();
    }

    private void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    StringBuilder result = new StringBuilder();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            String date = obj.getString("date"); // Change to your actual column name
                            String duration = obj.getString("duration"); // Change as needed
                            result.append("Your Trip on: ").append(date).append("\nIt will take: ").append(duration).append("\n\n");
                        }
                        textView.setText(result.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(Schedule.this, "Error fetching data", Toast.LENGTH_SHORT).show()
        );

        queue.add(jsonArrayRequest);
    }

    public void buttonAddEventsHandler(View v){

        Intent i= new Intent(Schedule.this,SecondActivity.class);
        startActivity(i);
    }
}