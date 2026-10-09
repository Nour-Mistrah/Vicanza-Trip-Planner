package com.example.vicanzatripplanner.ui.home;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.example.vicanzatripplanner.R;
import com.example.vicanzatripplanner.ReservationActivity;



public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    private Context context;

    private ReservationActivity reservationActivity;
    private List<Hotel> hotelList;

    private List<Hotel> selectedhotels = new ArrayList<>();


    public HotelAdapter(Context context, List<Hotel> hotelList) {
        this.context = context;
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (hotelList == null || hotelList.isEmpty()) return;

        Hotel hotel = hotelList.get(position);
        if (hotel == null) return;

        Context context = holder.itemView.getContext();

        holder.textViewName.setText(hotel.getName());
        holder.textViewLocation.setText(hotel.getLocation());
        holder.textViewPrice.setText(String.format("$%.2f", hotel.getPrice()));
        Glide.with(context).load(hotel.getimage_url()).into(holder.imageViewHotel);


        // Handle Select Button Click
        holder.selectButton.setOnClickListener(v -> {

            if (selectedhotels.contains(hotel)) {
                selectedhotels.remove(hotel); // Deselect if already selected
                holder.selectButton.setText("Select");
            } else {
                selectedhotels.add(hotel); // Add to selected list
                holder.selectButton.setText("Selected");
            }
        });


    }

    @Override
    public int getItemCount() {
        return hotelList == null ? 0 : hotelList.size();
    }

    public List <Hotel> getSelectedhotels() {
        return selectedhotels;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button selectButton;
        TextView textViewName, textViewLocation, textViewPrice;
        ImageView imageViewHotel;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageViewHotel = itemView.findViewById(R.id.imageViewHotel);
            selectButton = itemView.findViewById(R.id.selectButton);
        }
    }
}