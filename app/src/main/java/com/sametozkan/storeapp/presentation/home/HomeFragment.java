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
import com.sametozkan.storeapp.databinding.ItemCategoryBinding;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.presentation.category.CategoryActivity;
import com.sametozkan.storeapp.presentation.category.CategoryClickListener;
import com.sametozkan.storeapp.presentation.home.adapter.ProductAdapter;
import com.sametozkan.storeapp.util.Categories;
import com.sametozkan.storeapp.util.IntentConstants;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class HomeFragment extends Fragment implements CategoryClickListener {

    private static final String TAG = "HomeFragment";

    private FragmentHomeBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;

    private HomeViewModel homeViewModel;

    private final Map<Categories, ItemCategoryBinding> bindingMap = new HashMap<>();

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
        initBindingMap();
        for(Categories category : homeViewModel.getSelectedCategories()){
            setCategory(category, bindingMap.get(category));
        }
    }

    private void initBindingMap(){
        bindingMap.put(Categories.MENS_CLOTHING, binding.mensClothing);
        bindingMap.put(Categories.WOMENS_CLOTHING, binding.womensClothing);
        bindingMap.put(Categories.JEWELERY, binding.jewelery);
        bindingMap.put(Categories.ELECTRONICS, binding.electronics);
    }

    private void setCategory(Categories category, ItemCategoryBinding binding){
        binding.setCategory(category);
        binding.setAdapter(new ProductAdapter(getContext()));
    }

    @Override
    public void onCategoryClicked(Categories category) {
        Intent intent = new Intent(getContext(), CategoryActivity.class);
        intent.putExtra(IntentConstants.CATEGORY, category);
        startActivity(intent);
    }
}
