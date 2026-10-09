
package com.example.vicanzatripplanner;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PackagesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PackagesAdapter adapter;
    private List<PackageItem> packageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_packages);

        recyclerView = findViewById(R.id.recyclerViewHotels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        packageList = new ArrayList<>();
        adapter = new PackagesAdapter(this, packageList);
        recyclerView.setAdapter(adapter);

        fetchPackages();
    }

    private void fetchPackages() {
        String url = "http://10.0.2.2/android_api/get_packages.php"; // Replace with your actual URL

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                packageList.add(new PackageItem(
                                        obj.getInt("Packageid"),
                                        obj.getString("Packagename"),
                                        obj.getString("Packagelocation"),
                                        obj.getDouble("Packageprice"),
                                        obj.getString("Packageimage_url")
                                ));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PackagesActivity.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PackagesActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}