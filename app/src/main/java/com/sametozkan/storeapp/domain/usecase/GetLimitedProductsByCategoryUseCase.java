package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetLimitedProductsByCategoryUseCase{

    private ProductRepository productRepository;

    @Inject
    public GetLimitedProductsByCategoryUseCase(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Observable<List<Product>> execute(String category, int count){
        return productRepository.getLimitedProductsByCategory(category, count);
    }
}
