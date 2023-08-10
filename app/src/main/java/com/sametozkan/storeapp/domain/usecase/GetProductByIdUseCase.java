package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.repository.ProductRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetProductByIdUseCase extends UseCase<Single<Product>, Integer> {

    private ProductRepository productRepository;

    @Inject
    public GetProductByIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Single<Product> execute(Integer productId) {
        return productRepository.getProductById(productId);
    }

    @Override
    public Single<Product> execute() {
        throw new UnsupportedOperationException();
    }
}
