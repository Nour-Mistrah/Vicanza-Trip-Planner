package com.example.vicanzatripplanner.ui.notifications;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vicanzatripplanner.R;

import java.util.List;



public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {
    private Context context;
    private List<Restaurants> restaurantsList;

    public RestaurantsAdapter(Context context, List<Restaurants> restaurantsList) {
        this.context = context;
        this.restaurantsList = restaurantsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurants, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (restaurantsList == null || restaurantsList.isEmpty()) return;

        Restaurants restaurant = restaurantsList.get(position);
        if (restaurant == null) return;

        holder.textViewRName.setText(restaurant.getRName());
        holder.textViewRLocation.setText(restaurant.getRLocation());
        holder.textViewRPrice.setText(String.format("$%.2f", restaurant.getRPrice()));
        Glide.with(context).load(restaurant.getRImageUrl()).into(holder.imageViewRestaurants);

    }

    @Override
    public int getItemCount() {
        return restaurantsList == null ? 0 : restaurantsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRName, textViewRLocation, textViewRPrice;
        ImageView imageViewRestaurants;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewRName = itemView.findViewById(R.id.textViewRName);
            textViewRLocation = itemView.findViewById(R.id.textViewRLocation);
            textViewRPrice = itemView.findViewById(R.id.textViewRPrice);
            imageViewRestaurants = itemView.findViewById(R.id.imageViewRestaurants);
        }
    }
}
