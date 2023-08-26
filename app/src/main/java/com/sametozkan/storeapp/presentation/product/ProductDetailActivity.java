package com.sametozkan.storeapp.presentation.product;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.data.datasource.local.sharedpreferences.ShoppingCart;
import com.sametozkan.storeapp.databinding.ActivityProductDetailBinding;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.util.IntentConstants;

import javax.inject.Inject;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailClickListener {

    private static final String TAG = "ProductDetailActivity";

    @Inject
    ViewModelFactory viewModelFactory;

    private ProductDetailViewModel productDetailViewModel;
    private ActivityProductDetailBinding activityProductDetailBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        activityProductDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        setViewModel();
        checkIntent();
        observeProductById();
        observeProduct();
        setClickListener();
    }

    private void setClickListener() {
        activityProductDetailBinding.setClickListener(this);
    }

    private void setViewModel() {
        productDetailViewModel = new ViewModelProvider(this, viewModelFactory).get(ProductDetailViewModel.class);
    }

    private void checkIntent() {
        if (getIntent().getExtras() != null) {
            int productId = getIntent().getIntExtra(IntentConstants.PRODUCT_ID, 0);
            if (productId != 0) {
                productDetailViewModel.getProductId().setValue(productId);
            }
        }
    }

    private void observeProductById() {
        productDetailViewModel.getProductId().observe(this, (productId) -> {
            if (productId != null) {
                productDetailViewModel.fetchProductById(productId);
            }
        });
    }

    private void observeProduct() {
        productDetailViewModel.getProduct().observe(this, product -> {
            activityProductDetailBinding.setProduct(product);
        });
    }

    @Override
    public void onAddToCartButtonClicked(int productId) {
        if (activityProductDetailBinding.getProduct() != null) {
            ShoppingCart shoppingCart = new ShoppingCart(this,
                    productDetailViewModel.getCurrentUser().getEmail());
            shoppingCart.addProductIdToCart(productId);
            Toast.makeText(this, "Product added to cart successfully!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFavoriteButtonClicked() {
        //
    }
}
