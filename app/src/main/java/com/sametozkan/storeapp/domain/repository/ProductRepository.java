package com.sametozkan.storeapp.domain.repository;


import com.sametozkan.storeapp.data.datasource.remote.ApiService;
import com.sametozkan.storeapp.domain.model.Product;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface ProductRepository {
    Observable<List<Product>> getAllProducts();

    Single<Product> getProductById(int productId);

    Observable<List<Product>> getProductsByCategory(String category);

    Observable<List<Product>> getLimitedProductsByCategory(String category, int count);

}
