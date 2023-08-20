package com.sametozkan.storeapp.data.datasource.remote;

import com.sametozkan.storeapp.data.dto.ProductDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("products")
    Observable<List<ProductDTO>> getAllProducts();

    @GET("products/{productId}")
    Single<ProductDTO> getProductById(@Path("productId") int productId);

    @GET("products/categories")
    Single<List<String>> getCategoryList();

    @GET("products/category/{category}")
    Observable<List<ProductDTO>> getProductsByCategory(@Path("category") String category);

    @GET("products/category/{category}")
    Observable<List<ProductDTO>> getLimitedProductsByCategory(
            @Path("category") String category,
            @Query("limit") int limit);
}
