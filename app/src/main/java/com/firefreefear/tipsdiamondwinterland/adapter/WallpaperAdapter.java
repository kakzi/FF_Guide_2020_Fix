package com.firefreefear.tipsdiamondwinterland.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.firefreefear.tipsdiamondwinterland.R;
import com.firefreefear.tipsdiamondwinterland.model.WallpaperModel;

import java.util.ArrayList;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {

    private ArrayList<WallpaperModel> mData = new ArrayList<>();
    public void setWallpaper(ArrayList<WallpaperModel> items) {
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
    public WallpaperAdapter.WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallpaper, parent, false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WallpaperAdapter.WallpaperViewHolder holder, int position) {
        final WallpaperModel wallpaperModel = mData.get(position);
        Glide.with(holder.itemView.getContext())
                .load(wallpaperModel.getImage_url())
                .placeholder(R.drawable.placeholder_image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.img_wallpaper);
        holder.title.setText(wallpaperModel.getTitle());
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

    public class WallpaperViewHolder extends RecyclerView.ViewHolder {

        ImageView img_wallpaper;
        TextView title;

        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            img_wallpaper = itemView.findViewById(R.id.img_wallpaper);
            title = itemView.findViewById(R.id.title);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(WallpaperModel data);
    }
}
