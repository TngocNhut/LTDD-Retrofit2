package com.tngocnhat.retrofit2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tngocnhat.retrofit2.R;
import com.tngocnhat.retrofit2.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.VH> {

    private Context context;
    private List<Category> list = new ArrayList<>();

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<Category> items) {
        list.clear();
        if (items != null) list.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Category c = list.get(position);

        holder.title.setText(c.getName());   // <-- đổi Title → Name

        Glide.with(context)
                .load(c.getImages())        // <-- đổi Image → Images
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;

        public VH(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgCat);
            title = itemView.findViewById(R.id.tvTitle);
        }
    }
}
