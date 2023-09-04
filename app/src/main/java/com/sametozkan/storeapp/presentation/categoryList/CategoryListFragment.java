package com.sametozkan.storeapp.presentation.categoryList;

import android.os.Bundle;
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
import com.sametozkan.storeapp.databinding.FragmentCategoryListBinding;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.util.Utils;

import javax.inject.Inject;

public class CategoryListFragment extends Fragment {

    private FragmentCategoryListBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;

    private CategoryListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MyApplication) getContext().getApplicationContext()).getAppComponent().inject(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_list, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViewModel();
        setRecyclerView();
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(CategoryListViewModel.class);
    }

    public void setRecyclerView() {
        binding.setAdapter(new CategoryListAdapter(getContext()));
        binding.setViewModel(viewModel);
    }
}
