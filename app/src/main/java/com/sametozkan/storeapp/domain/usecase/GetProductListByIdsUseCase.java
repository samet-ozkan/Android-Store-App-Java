package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.repository.FirebaseRepository;
import com.sametozkan.storeapp.domain.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class GetProductListByIdsUseCase {

    private ProductRepository productRepository;

    @Inject
    public GetProductListByIdsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Single<List<Product>> execute(List<Integer> productIds) {
        return Observable.fromIterable(productIds)
                .flatMapSingle(productId -> productRepository.getProductById(productId))
                .toList();
    }
}
