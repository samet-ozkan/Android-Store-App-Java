package com.sametozkan.storeapp.presentation.pastorders;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.domain.model.Order;
import com.sametozkan.storeapp.domain.usecase.GetPastOrdersUseCase;
import com.sametozkan.storeapp.util.Callback;
import com.sametozkan.storeapp.util.States;

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

    private final MutableLiveData<States> state = new MutableLiveData<>(States.LOADING);

    @Inject
    public PastOrdersViewModel(GetPastOrdersUseCase getPastOrdersUseCase) {
        this.getPastOrdersUseCase = getPastOrdersUseCase;
        fetchPastOrders();
    }

    public void fetchPastOrders() {
        getPastOrdersUseCase.execute(new Callback<List<Order>>() {
            @Override
            public void onSuccess(List<Order> result) {
                if (result.isEmpty()) {
                    state.postValue(States.EMPTY);
                } else {
                    pastOrders.postValue(result);
                    state.postValue(States.SUCCESS);
                }

            }

            @Override
            public void onFailure(String errorMessage) {
                state.postValue(States.ERROR);
                Log.e(TAG, "onFailure: " + errorMessage);
            }
        });
    }

    public LiveData<States> getState() {
        return state;
    }
}
