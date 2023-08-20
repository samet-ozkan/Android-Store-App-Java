package com.sametozkan.storeapp.presentation.category;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.ActivityCategoryBinding;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.presentation.home.adapter.ProductAdapter;
import com.sametozkan.storeapp.util.Constants;

import java.util.List;

import javax.inject.Inject;

public class CategoryActivity extends AppCompatActivity {

    private static final String TAG = "CategoryActivity";

    @Inject
    ViewModelFactory viewModelFactory;

    private CategoryViewModel categoryViewModel;

    private ActivityCategoryBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        categoryViewModel = new ViewModelProvider(this, viewModelFactory).get(CategoryViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category);
        binding.setLifecycleOwner(this);
        binding.setCategoryViewModel(categoryViewModel);
        //binding.setList((categoryViewModel.getProductList().getValue()));
        if (checkExtras()) {
            setRecyclerView();
            fetchData();
            //observeData();
        }
    }

    private void observeData(){
        categoryViewModel.getProductList().observe(this, products -> {
            Log.d(TAG, "observeData: " + products);
            binding.setCategoryViewModel(categoryViewModel);
        });
    }

    private boolean checkExtras() {
        if (getIntent().getExtras() != null && getIntent().getStringExtra(Constants.CATEGORY) != null) {
            return true;
        }
        return false;
    }

    private void fetchData() {
        Log.d(TAG, "fetchData: " + getIntent().getStringExtra(Constants.CATEGORY));
        categoryViewModel.fetchProductsByCategory(getIntent().getStringExtra(Constants.CATEGORY));
    }

    private void setRecyclerView() {
        binding.setAdapter(new ProductAdapter(this));
    }
}
