package com.sametozkan.storeapp.presentation.product;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.ActivityProductDetailBinding;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.util.Constants;

import javax.inject.Inject;

public class ProductDetailActivity extends AppCompatActivity {

    private static final String TAG = "ProductDetailActivity";

    @Inject
    ViewModelFactory viewModelFactory;

    private ProductDetailViewModel productDetailViewModel;
    private ActivityProductDetailBinding activityProductDetailBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        activityProductDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        setViewModel();
        checkIntent();
        observeProductId();
        observeProduct();
    }

    private void setViewModel() {
        productDetailViewModel = new ViewModelProvider(this, viewModelFactory).get(ProductDetailViewModel.class);
    }

    private void checkIntent() {
        if (getIntent().getExtras() != null) {
            int productId = getIntent().getIntExtra(Constants.PRODUCT_ID, 0);
            if (productId != 0) {
                productDetailViewModel.getProductId().setValue(productId);
            }
        }
    }

    private void observeProductId() {
        productDetailViewModel.getProductId().observe(this, (productId) -> {
            if (productId != null) {
                productDetailViewModel.fetchProductById(productId);
            }
        });
    }

    private void observeProduct(){
        productDetailViewModel.getProduct().observe(this, product -> {
            activityProductDetailBinding.setProduct(product);
        });
    }
}
