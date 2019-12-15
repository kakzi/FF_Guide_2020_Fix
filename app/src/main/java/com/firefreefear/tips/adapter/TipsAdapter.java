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
import com.firefreefear.tips.model.TipsModel;

import java.util.ArrayList;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {

    private ArrayList<TipsModel> mData = new ArrayList<>();

    public void setData(ArrayList<TipsModel> items) {
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
    public TipsAdapter.TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tips, parent, false);
        return new TipsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TipsAdapter.TipsViewHolder holder, int position) {
        final TipsModel tipsModel = mData.get(position);
        holder.title.setText(tipsModel.getTitle());
        holder.desc.setText(tipsModel.getDesc());
        Glide.with(holder.itemView.getContext()).load(tipsModel.getImage_url())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.img_tips);
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

    public class TipsViewHolder extends RecyclerView.ViewHolder {

        ImageView img_tips;
        TextView title, desc;

        public TipsViewHolder(@NonNull View itemView) {
            super(itemView);
            img_tips = itemView.findViewById(R.id.img_tips);
            title = itemView.findViewById(R.id.tv_title_tips);
            desc = itemView.findViewById(R.id.tv_desc_tips);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(TipsModel data);
    }
}
