package com.sametozkan.storeapp.util;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.model.User;
import com.sametozkan.storeapp.presentation.categoryList.CategoryListAdapter;
import com.sametozkan.storeapp.presentation.home.adapter.ProductAdapter;
import com.sametozkan.storeapp.presentation.orderdetail.OrderDetailAdapter;
import com.sametozkan.storeapp.presentation.pastorders.PastOrdersAdapter;
import com.sametozkan.storeapp.presentation.shoppingcart.ShoppingCartAdapter;

import java.util.List;
import java.util.Locale;

public class BindingAdapter {

    private static final String TAG = "BindingAdapter";

    @androidx.databinding.BindingAdapter("submitList")
    public static void submitList(RecyclerView recyclerView, List list) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            if (adapter instanceof ListAdapter) {
                ((ListAdapter) adapter).setList(list);
            } else {
                Log.e(TAG, "submitList: ", new Throwable("Unsupported adapter"));
            }
        } else {
            Log.e(TAG, "submitList: ", new Throwable("Null adapter"));
        }
    }

    @androidx.databinding.BindingAdapter("setAdapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @androidx.databinding.BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    @androidx.databinding.BindingAdapter("bindUser")
    public static void bindUser(NavigationView navigationView, User user) {
        if (user != null) {
            View headerView = navigationView.getHeaderView(0);
            TextView fullName = headerView.findViewById(R.id.fullName);
            TextView email = headerView.findViewById(R.id.email);
            fullName.setText(user.getFullName());
            email.setText(user.getEmail());
        }
    }
}
