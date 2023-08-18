package com.sametozkan.storeapp.presentation.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.FragmentHomeBinding;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.presentation.home.adapter.ProductAdapter;

import java.util.List;

import javax.inject.Inject;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Inject
    HomeViewModel homeViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MyApplication) context.getApplicationContext()).getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(homeViewModel);
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
        binding.mensClothing.setAdapter(new ProductAdapter());
    }

    private void setWomensClothing() {
        binding.womensClothing.setAdapter(new ProductAdapter());
    }

    private void setJewelery() {
        binding.jewelery.setAdapter(new ProductAdapter());
    }

    private void setElectronics() {
        binding.electronics.setAdapter(new ProductAdapter());
    }

}
