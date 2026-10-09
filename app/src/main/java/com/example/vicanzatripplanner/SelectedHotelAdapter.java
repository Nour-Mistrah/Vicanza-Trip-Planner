package com.example.vicanzatripplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vicanzatripplanner.ui.home.Hotel;

import java.util.List;



public class SelectedHotelAdapter extends RecyclerView.Adapter<SelectedHotelAdapter.ViewHolder> {
    private Context context;
    private List<Hotel> hotelList;

    public SelectedHotelAdapter(Context context, List<Hotel> hotelList) {
        this.context = context;
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_selected_hotel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.textViewName.setText(hotel.getName());
        holder.textViewLocation.setText(hotel.getLocation());
        holder.textViewPrice.setText("$" + hotel.getPrice());
        Glide.with(context).load(hotel.getimage_url()).into(holder.imageViewHotel);
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewLocation, textViewPrice;
        ImageView imageViewHotel;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageViewHotel = itemView.findViewById(R.id.imageViewHotel);
        }
    }
}