package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.domain.model.Order;
import com.sametozkan.storeapp.domain.repository.FirebaseRepository;
import com.sametozkan.storeapp.util.Callback;

import java.util.List;

import javax.inject.Inject;

public class GetPastOrdersUseCase {

    private FirebaseRepository firebaseRepository;

    @Inject
    public GetPastOrdersUseCase(FirebaseRepository firebaseRepository) {
        this.firebaseRepository = firebaseRepository;
    }

    public void execute(Callback<List<Order>> callback) {
        firebaseRepository.getPastOrders(callback);
    }
}
