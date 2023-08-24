package com.sametozkan.storeapp.presentation.shoppingcart;

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
import com.sametozkan.storeapp.data.datasource.local.sharedpreferences.ShoppingCart;
import com.sametozkan.storeapp.databinding.FragmentShoppingCartBinding;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.presentation.ViewModelFactory;

import javax.inject.Inject;

public class ShoppingCartFragment extends Fragment implements ShoppingCartItemClickListener {

    @Inject
    ViewModelFactory viewModelFactory;

    private FragmentShoppingCartBinding binding;
    private ShoppingCartViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MyApplication) getContext().getApplicationContext()).getAppComponent().inject(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_cart, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViewModel();
        viewModel.fetchCartItems(getContext());
        binding.setViewModel(viewModel);
        binding.setShoppingCartAdapter(new ShoppingCartAdapter(this));
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(ShoppingCartViewModel.class);
    }

    @Override
    public void onRemoveItemClicked(Product product) {
        viewModel.removeCartItem(getContext(), product.getId());
        viewModel.fetchCartItems(getContext());
    }
}
