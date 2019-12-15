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
import com.firefreefear.tips.model.Character;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private ArrayList<Character> mData = new ArrayList<>();
    public void setCharacter(ArrayList<Character> items) {
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
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CharacterAdapter.CharacterViewHolder holder, final int position) {
        final Character character = mData.get(position);
        holder.name.setText(character.getName());
        Glide.with(holder.itemView.getContext()).load(character.getImage_url())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.img_character);
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

    public class CharacterViewHolder extends RecyclerView.ViewHolder {

        ImageView img_character;
        TextView name;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            img_character = itemView.findViewById(R.id.img_character);
            name = itemView.findViewById(R.id.name);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Character data);
    }
}
