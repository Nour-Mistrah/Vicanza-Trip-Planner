package com.example.vicanzatripplanner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.PackageViewHolder> {

    private Context context;
    private List<PackageItem> packageList;

    public PackagesAdapter(Context context, List<PackageItem> packageList) {
        this.context = context;
        this.packageList = packageList;
    }

    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_package, parent, false);
        return new PackageViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull PackageViewHolder holder, int position) {
        PackageItem pack = packageList.get(position);
        holder.name.setText(pack.getPackagename());
        holder.location.setText("Location: " + pack.getPackagelocation());
        holder.price.setText("Price: $" + pack.getPackageprice());

        Glide.with(context).load(pack.getPackageimage_url()).into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ReservationActivity.class);

            intent.putExtra("Packageid", packageList.get(position).getId()); // Pass price
            context.startActivity(intent);
        });




    }

    @Override
    public int getItemCount() {
        return packageList.size();
    }

    static class PackageViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, price;
        ImageView image;

        public PackageViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewPackageName);
            location = itemView.findViewById(R.id.textViewPackageLocation);
            price = itemView.findViewById(R.id.textViewPackagePrice);
            image = itemView.findViewById(R.id.imageViewPackage);
        }
    }
}