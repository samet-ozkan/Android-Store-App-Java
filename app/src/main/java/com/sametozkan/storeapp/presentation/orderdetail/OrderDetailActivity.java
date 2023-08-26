package com.sametozkan.storeapp.presentation.orderdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.ActivityOrderDetailBinding;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.presentation.product.ProductDetailActivity;
import com.sametozkan.storeapp.util.IntentConstants;

import javax.inject.Inject;

public class OrderDetailActivity extends AppCompatActivity implements ProductClickListener {

    private static final String TAG = "OrderDetailActivity";

    @Inject
    ViewModelFactory viewModelFactory;

    private ActivityOrderDetailBinding binding;

    private OrderDetailViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail);
        binding.setLifecycleOwner(this);
        if (checkExtras()) {
            setViewModel();
            String orderId = getIntent().getStringExtra(IntentConstants.ORDER_ID);
            viewModel.fetchOrder(orderId);
            observeOrder();
            bindVariables();
        }
    }

    private void bindVariables() {
        binding.setViewModel(viewModel);
        binding.setAdapter(new OrderDetailAdapter(this));
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(OrderDetailViewModel.class);
    }

    private void observeOrder() {
        viewModel.getOrder().observe(this, order -> {
            if (order != null) {
                viewModel.fetchProductList(order.getProductIds());
            }
        });
    }

    private boolean checkExtras() {
        if (getIntent().getExtras() != null) {
            return true;
        }
        return false;
    }

    @Override
    public void onProductClicked(Product product) {
        final Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(IntentConstants.PRODUCT_ID, product.getId());
        startActivity(intent);
    }
}
