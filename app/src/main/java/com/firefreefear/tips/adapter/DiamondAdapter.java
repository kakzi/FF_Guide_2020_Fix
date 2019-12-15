package com.firefreefear.tips.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firefreefear.tips.R;
import com.firefreefear.tips.model.Diamond;

import java.util.ArrayList;


public class DiamondAdapter extends RecyclerView.Adapter<DiamondAdapter.DiamondViewHolder> {

    private ArrayList<Diamond> mData = new ArrayList<>();
    public void setDiamond(ArrayList<Diamond> items){
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
    public DiamondAdapter.DiamondViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diamond, parent, false);
        return new DiamondViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DiamondAdapter.DiamondViewHolder holder, int position) {
        final Diamond diamond = mData.get(position);
        holder.title.setText(diamond.getTitle());
        holder.desc.setText(diamond.getDesc());
        holder.sub_tips.setText(diamond.getImage_url());
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

    public class DiamondViewHolder extends RecyclerView.ViewHolder {

        TextView title, sub_tips, desc;

        public DiamondViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            sub_tips = itemView.findViewById(R.id.sub_tips);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Diamond data);
    }
}
