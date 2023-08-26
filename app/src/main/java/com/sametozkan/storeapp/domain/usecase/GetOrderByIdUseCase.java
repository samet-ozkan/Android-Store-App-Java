package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.domain.model.Order;
import com.sametozkan.storeapp.domain.repository.FirebaseRepository;
import com.sametozkan.storeapp.util.Callback;

import javax.inject.Inject;

public class GetOrderByIdUseCase {

    private FirebaseRepository firebaseRepository;

    @Inject
    public GetOrderByIdUseCase(FirebaseRepository firebaseRepository){
        this.firebaseRepository = firebaseRepository;
    }

    public void execute(String orderId, Callback<Order> callback){
        firebaseRepository.getOrderById(orderId, callback);
    }
}
