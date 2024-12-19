package com.example.proyectodsa_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectodsa_android.R;
import com.example.proyectodsa_android.models.Badge;

import java.util.List;

public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder> {
    private List<Badge> badges;

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BadgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_badge, parent, true);
        return new BadgeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BadgeViewHolder holder, int position) {
        Badge badge = badges.get(position);
        holder.textView.setText(badge.getName());
        Glide.with(holder.imageView.getContext())
                .load(badge.getImageUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return badges != null ? badges.size() : 0;
    }

    static class BadgeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        BadgeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
