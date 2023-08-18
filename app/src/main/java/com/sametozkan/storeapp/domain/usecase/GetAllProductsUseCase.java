package com.sametozkan.storeapp.domain.usecase;

import android.util.Log;

import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetAllProductsUseCase {

    private static final String TAG = "GetAllProductsUseCase";

    private ProductRepository productRepository;

    @Inject
    public GetAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
        if (this.productRepository == null) {
            Log.d(TAG, "GetAllProductsUseCase: productRepository is null");
        } else
            Log.d(TAG, "GetAllProductsUseCase: productRepository is not null");

    }

    public Observable<List<Product>> execute() {
        return productRepository.getAllProducts();
    }
}
