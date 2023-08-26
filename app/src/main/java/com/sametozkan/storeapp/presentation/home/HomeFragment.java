package com.sametozkan.storeapp.presentation.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.FragmentHomeBinding;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.presentation.category.CategoryActivity;
import com.sametozkan.storeapp.presentation.category.CategoryClickListener;
import com.sametozkan.storeapp.presentation.home.adapter.ProductAdapter;
import com.sametozkan.storeapp.util.Categories;
import com.sametozkan.storeapp.util.IntentConstants;

import javax.inject.Inject;

public class HomeFragment extends Fragment implements CategoryClickListener {

    private static final String TAG = "HomeFragment";

    private FragmentHomeBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;

    private HomeViewModel homeViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MyApplication) context.getApplicationContext()).getAppComponent().inject(this);
        homeViewModel = new ViewModelProvider(this, viewModelFactory).get(HomeViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(homeViewModel);
        binding.setCategoryClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setMensClothing();
        setWomensClothing();
        setJewelery();
        setElectronics();
    }

    private void setMensClothing() {
        binding.mensClothing.setCategoryName(Categories.MENS_CLOTHING.categoryTitle);
        binding.mensClothing.setAdapter(new ProductAdapter(getContext()));
    }

    private void setWomensClothing() {
        binding.womensClothing.setCategoryName(Categories.WOMENS_CLOTHING.categoryTitle);
        binding.womensClothing.setAdapter(new ProductAdapter(getContext()));
    }

    private void setJewelery() {
        binding.jewelery.setCategoryName(Categories.JEWELERY.categoryTitle);
        binding.jewelery.setAdapter(new ProductAdapter(getContext()));
    }

    private void setElectronics() {
        binding.electronics.setCategoryName(Categories.ELECTRONICS.categoryTitle);
        binding.electronics.setAdapter(new ProductAdapter(getContext()));
    }

    @Override
    public void onCategoryClicked(String categoryName) {
        final Intent intent = new Intent(getContext(), CategoryActivity.class);
        if (categoryName.equals(Categories.MENS_CLOTHING.categoryTitle)) {
            intent.putExtra(IntentConstants.CATEGORY, Categories.MENS_CLOTHING.category);
            startActivity(intent);
        } else if (categoryName.equals(Categories.WOMENS_CLOTHING.categoryTitle)) {
            intent.putExtra(IntentConstants.CATEGORY, Categories.WOMENS_CLOTHING.category);
            startActivity(intent);
        } else if (categoryName.equals(Categories.JEWELERY.categoryTitle)) {
            intent.putExtra(IntentConstants.CATEGORY, Categories.JEWELERY.category);
            startActivity(intent);
        } else if (categoryName.equals(Categories.ELECTRONICS.categoryTitle)) {
            intent.putExtra(IntentConstants.CATEGORY, Categories.ELECTRONICS.category);
            startActivity(intent);
        } else {
            Log.e(TAG, "onCategoryClicked: Invalid category!", new IllegalArgumentException());
        }
    }
}
