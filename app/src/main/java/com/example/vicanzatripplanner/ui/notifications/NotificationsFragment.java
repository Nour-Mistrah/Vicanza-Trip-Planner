package com.example.vicanzatripplanner.ui.notifications;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.vicanzatripplanner.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class NotificationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RestaurantsAdapter adapter;
    private ArrayList<Restaurants> restaurantsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewRestaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        restaurantsList = new ArrayList<>();
        adapter = new RestaurantsAdapter(getContext(), restaurantsList);
        recyclerView.setAdapter(adapter);

        fetchRestaurants();

        return view;
    }

    private void fetchRestaurants() {
        String url = "http://10.0.2.2/android_api/get_restaurants.php"; // Replace with your actual URL
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            restaurantsList.clear(); // Clear existing data
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject restaurant = response.getJSONObject(i);
                                restaurantsList.add(new Restaurants(
                                        restaurant.getInt("Rid"),
                                        restaurant.getString("Rname"),
                                        restaurant.getString("Rlocation"),
                                        restaurant.getDouble("Rprice"),
                                        restaurant.getString("Rimage_url")
                                ));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error parsing data: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getContext(), "Volley Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(jsonArrayRequest);
    }
}