package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetProductsByCategoryUseCase {

    private ProductRepository productRepository;

    @Inject
    public GetProductsByCategoryUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Observable<List<Product>> execute(String category) {
        return productRepository.getProductsByCategory(category);
    }
}
