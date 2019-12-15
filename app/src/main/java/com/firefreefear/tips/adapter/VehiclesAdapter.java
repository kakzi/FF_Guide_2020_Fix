package com.firefreefear.tips.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.firefreefear.tips.R;
import com.firefreefear.tips.model.Vehicles;

import java.util.ArrayList;

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.VehiclesViewHolder> {

    private ArrayList<Vehicles> mData = new ArrayList<>();
    public void setVehicles(ArrayList<Vehicles> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }


    @NonNull
    @Override
    public VehiclesAdapter.VehiclesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicles, parent, false);
        return new VehiclesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VehiclesAdapter.VehiclesViewHolder holder, int position) {
        final Vehicles vehicles = mData.get(position);
        holder.title.setText(vehicles.getName());
        Glide.with(holder.itemView.getContext()).load(vehicles.getImage_url())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.img_vehicles);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(mData.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class VehiclesViewHolder extends RecyclerView.ViewHolder {

        ImageView img_vehicles;
        TextView title;

        public VehiclesViewHolder(@NonNull View itemView) {
            super(itemView);
            img_vehicles = itemView.findViewById(R.id.img_vehicles);
            title = itemView.findViewById(R.id.title);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Vehicles data);
    }
}
