package com.sametozkan.storeapp.presentation.pastorders;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.domain.model.Order;
import com.sametozkan.storeapp.domain.usecase.GetPastOrdersUseCase;
import com.sametozkan.storeapp.util.Callback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PastOrdersViewModel extends ViewModel {

    private static final String TAG = "PastOrdersViewModel";

    private GetPastOrdersUseCase getPastOrdersUseCase;

    private final MutableLiveData<List<Order>> pastOrders = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Order>> getPastOrders() {
        return pastOrders;
    }

    @Inject
    public PastOrdersViewModel(GetPastOrdersUseCase getPastOrdersUseCase) {
        this.getPastOrdersUseCase = getPastOrdersUseCase;
        fetchPastOrders();
    }

    public void fetchPastOrders() {
        getPastOrdersUseCase.execute(new Callback<List<Order>>() {
            @Override
            public void onSuccess(List<Order> result) {
                pastOrders.postValue(result);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e(TAG, "onFailure: " + errorMessage);
            }
        });
    }
}
