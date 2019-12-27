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
import com.firefreefear.tipsdiamondwinterland.model.MenuModel;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {


    private ArrayList<MenuModel> mData = new ArrayList<>();
    public void setData(ArrayList<MenuModel> items) {
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
    public MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuAdapter.MenuViewHolder holder, final int position) {
        final MenuModel menuModel = mData.get(position);
        holder.tvMenu.setText(menuModel.getTitle_menu());
        holder.tvSub.setText(menuModel.getSub_title());
        Glide.with(holder.itemView.getContext()).load(menuModel.getImage_url()).transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.img_menu);
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

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvMenu, tvSub;
        ImageView img_menu;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMenu = itemView.findViewById(R.id.tv_title_menu);
            tvSub = itemView.findViewById(R.id.tv_sub_menu);
            img_menu = itemView.findViewById(R.id.img_menu);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(MenuModel data);
    }
}
