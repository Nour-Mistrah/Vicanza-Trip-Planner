package com.example.vicanzatripplanner.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.vicanzatripplanner.Schedule;
import com.example.vicanzatripplanner.SecondActivity;
import com.example.vicanzatripplanner.databinding.FragmentGalleryBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    TextView textView;

    String url = "http://10.0.2.2/android_api/getTripDetails.php"; // Change this to your actual server IP


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());

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
                error -> Log.e("VolleyError", error.toString())
        );

        queue.add(jsonArrayRequest);
    }


}