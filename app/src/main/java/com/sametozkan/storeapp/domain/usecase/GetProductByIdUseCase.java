package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.repository.ProductRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetProductByIdUseCase{

    private ProductRepository productRepository;

    @Inject
    public GetProductByIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Single<Product> execute(int productId) {
            return productRepository.getProductById(productId);
    }

}
