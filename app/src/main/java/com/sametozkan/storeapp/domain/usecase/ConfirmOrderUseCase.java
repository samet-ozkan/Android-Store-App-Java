package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.domain.model.Order;
import com.sametozkan.storeapp.domain.repository.FirebaseRepository;
import com.sametozkan.storeapp.util.Callback;

import javax.inject.Inject;

public class ConfirmOrderUseCase {

    private FirebaseRepository firebaseRepository;

    @Inject
    public ConfirmOrderUseCase(FirebaseRepository firebaseRepository) {
        this.firebaseRepository = firebaseRepository;
    }

    public void execute(Order order, Callback<Void> callback) {
        firebaseRepository.confirmOrder(order, callback);
    }
}
