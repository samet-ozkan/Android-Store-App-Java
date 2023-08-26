package com.sametozkan.storeapp.presentation.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.FragmentShoppingCartBinding;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.presentation.product.ProductDetailActivity;
import com.sametozkan.storeapp.util.Callback;
import com.sametozkan.storeapp.util.IntentConstants;

import javax.inject.Inject;

public class ShoppingCartFragment extends Fragment implements ShoppingCartClickListener {

    private static final String TAG = "ShoppingCartFragment";

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
        binding.setClickListener(this);
        observeProductList();
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(ShoppingCartViewModel.class);
    }

    @Override
    public void onConfirmOrderClicked() {
        viewModel.confirmOrder(new Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                viewModel.clearCart(getContext());
                viewModel.fetchCartItems(getContext());
                Toast.makeText(getContext(), "Order confirmed successfully!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e(TAG, "onFailure: " + errorMessage);
            }
        });
    }

    @Override
    public void onItemClicked(Product product) {
        final Intent intent = new Intent(getContext(), ProductDetailActivity.class);
        intent.putExtra(IntentConstants.PRODUCT_ID, product.getId());
        getContext().startActivity(intent);
    }

    @Override
    public void onRemoveItemClicked(Product product) {
        viewModel.removeCartItem(getContext(), product.getId());
        viewModel.fetchCartItems(getContext());
    }

    private void observeProductList() {
        viewModel.getProductList().observe(getViewLifecycleOwner(), productList -> {
            if (productList != null) {
                viewModel.calculateTotalAmount(productList);
            }
        });
    }
}
