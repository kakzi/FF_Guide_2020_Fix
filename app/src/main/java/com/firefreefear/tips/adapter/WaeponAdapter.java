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
import com.firefreefear.tips.model.WaeponModel;

import java.util.ArrayList;

public class WaeponAdapter extends RecyclerView.Adapter<WaeponAdapter.WaeponViewHolder> {

    private ArrayList<WaeponModel> mData = new ArrayList<>();
    public void setWaepon(ArrayList<WaeponModel> items) {
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
    public WaeponAdapter.WaeponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_waepon, parent, false);
        return new WaeponViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WaeponAdapter.WaeponViewHolder holder, int position) {
        final WaeponModel waeponModel = mData.get(position);
        holder.name_waepon.setText(waeponModel.getName_waepon());
        Glide.with(holder.itemView.getContext()).load(waeponModel.getImage_url())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.img_waepon);
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

    public class WaeponViewHolder extends RecyclerView.ViewHolder {

        ImageView img_waepon;
        TextView name_waepon;

        public WaeponViewHolder(@NonNull View itemView) {
            super(itemView);
            img_waepon = itemView.findViewById(R.id.img_waepon);
            name_waepon = itemView.findViewById(R.id.name_waepon);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(WaeponModel data);
    }
}
