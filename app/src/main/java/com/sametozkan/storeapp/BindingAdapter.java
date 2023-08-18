package com.sametozkan.storeapp;

import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.presentation.home.adapter.ProductAdapter;

import java.util.List;

public class BindingAdapter {

    private static final String TAG = "BindingAdapter";

    @androidx.databinding.BindingAdapter("submitList")
    public static void submitList(RecyclerView recyclerView, List<Product> list) {
        if (recyclerView.getAdapter() != null) {
            ((ProductAdapter) recyclerView.getAdapter()).setProductList(list);
        }
    }

    @androidx.databinding.BindingAdapter("setAdapter")
    public static void setAdapter(RecyclerView recyclerView, ProductAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @androidx.databinding.BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}
