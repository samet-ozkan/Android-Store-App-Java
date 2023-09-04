package com.sametozkan.storeapp.presentation.category;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.ActivityCategoryBinding;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.presentation.home.adapter.ProductAdapter;
import com.sametozkan.storeapp.util.IntentConstants;
import com.sametozkan.storeapp.util.Utils;

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
        if (checkExtras()) {
            setRecyclerView();
            fetchData();
        }
    }

    private boolean checkExtras() {
        if (getIntent().getExtras() != null && getIntent().getStringExtra(IntentConstants.CATEGORY) != null) {
            return true;
        }
        return false;
    }

    private void fetchData() {
        Log.d(TAG, "fetchData: " + getIntent().getStringExtra(IntentConstants.CATEGORY));
        categoryViewModel.fetchProductsByCategory(getIntent().getStringExtra(IntentConstants.CATEGORY));
    }

    private void setRecyclerView() {
        binding.setAdapter(new ProductAdapter(this));
    }
}
