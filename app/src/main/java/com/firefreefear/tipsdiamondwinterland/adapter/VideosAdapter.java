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
import com.firefreefear.tipsdiamondwinterland.model.VideoModel;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosViewHolder> {

    private ArrayList<VideoModel> mData = new ArrayList<>();
    public void setVideos(ArrayList<VideoModel> items) {
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
    public VideosAdapter.VideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideosAdapter.VideosViewHolder holder, int position) {
        final VideoModel videoModel = mData.get(position);
        holder.title.setText(videoModel.getTitle());
        Glide.with(holder.itemView.getContext()).load(videoModel.getUrl_tumb())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.img_tumb);
        holder.img_button_play.setOnClickListener(new View.OnClickListener() {
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

    public class VideosViewHolder extends RecyclerView.ViewHolder {

        ImageView img_tumb, img_button_play;
        TextView title;

        public VideosViewHolder(@NonNull View itemView) {
            super(itemView);
            img_button_play = itemView.findViewById(R.id.img_play_button);
            img_tumb = itemView.findViewById(R.id.img_tumb);
            title = itemView.findViewById(R.id.title);

        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(VideoModel data);
    }
}
