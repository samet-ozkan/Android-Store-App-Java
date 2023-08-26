package com.sametozkan.storeapp.data.repository;

import android.util.Log;

import com.sametozkan.storeapp.data.datasource.remote.ApiService;
import com.sametozkan.storeapp.data.mapper.ProductMapper;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.repository.ProductRepository;
import com.sametozkan.storeapp.util.Callback;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ProductRepositoryImpl implements ProductRepository {

    private static final String TAG = "ProductRepositoryImpl";

    private ApiService apiService;

    @Inject
    public ProductRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
        if (apiService == null)
            Log.d(TAG, "ProductRepositoryImpl: apiService is null");
        else
            Log.d(TAG, "ProductRepositoryImpl: not null");
    }

    @Override
    public Observable<List<Product>> getAllProducts() {
        Log.d(TAG, "getAllProducts: getAllProducts called");
        return apiService.getAllProducts().map(ProductMapper::toProductList);
    }

    @Override
    public Single<Product> getProductById(int productId) {
        return apiService.getProductById(productId).map(ProductMapper::toProduct);
    }

    @Override
    public Observable<List<Product>> getProductsByCategory(String category) {
        return apiService.getProductsByCategory(category).map(ProductMapper::toProductList);
    }

    @Override
    public Observable<List<Product>> getLimitedProductsByCategory(String category, int count) {
        return apiService.getLimitedProductsByCategory(category, count).map(ProductMapper::toProductList);
    }
}
