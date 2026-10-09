package com.example.vicanzatripplanner.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.example.vicanzatripplanner.R;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {
    private Context context;
    private List<Places> placesList;

    public PlacesAdapter(Context context, List<Places> placesList) {
        this.context = context;
        this.placesList = placesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_places, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Places place = placesList.get(position);

        if (place != null) {
            holder.textViewPName.setText(place.getPname());
            holder.textViewPLocation.setText(place.getPlocation());
            holder.textViewPPrice.setText(String.format("$%.2f", place.getPprice()));
            Glide.with(context).load(place.getPimageUrl()).into(holder.imageViewPlaces);


        }
    }

    @Override
    public int getItemCount() {
        return placesList == null ? 0 : placesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPName, textViewPLocation, textViewPPrice;
        ImageView imageViewPlaces;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewPName = itemView.findViewById(R.id.textViewPName);
            textViewPLocation = itemView.findViewById(R.id.textViewPLocation);
            textViewPPrice = itemView.findViewById(R.id.textViewPlPrice);
            imageViewPlaces = itemView.findViewById(R.id.imageViewPlaces);
        }
    }
}