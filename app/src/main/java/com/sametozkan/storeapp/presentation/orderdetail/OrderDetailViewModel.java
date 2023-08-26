package com.sametozkan.storeapp.presentation.orderdetail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.domain.model.Order;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.usecase.GetOrderByIdUseCase;
import com.sametozkan.storeapp.domain.usecase.GetProductByIdUseCase;
import com.sametozkan.storeapp.domain.usecase.GetProductListByIdsUseCase;
import com.sametozkan.storeapp.util.Callback;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrderDetailViewModel extends ViewModel {

    private static final String TAG = "OrderDetailViewModel";

    private CompositeDisposable disposable;
    private GetOrderByIdUseCase getOrderByIdUseCase;
    private GetProductListByIdsUseCase getProductListByIdsUseCase;

    private final MutableLiveData<Order> order = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> productList = new MutableLiveData<>();

    public LiveData<Order> getOrder() {
        return order;
    }

    public LiveData<List<Product>> getProductList() {
        return productList;
    }

    @Inject
    public OrderDetailViewModel(
            GetOrderByIdUseCase getOrderByIdUseCase,
            GetProductListByIdsUseCase getProductListByIdsUseCase
    ) {
        disposable = new CompositeDisposable();
        this.getProductListByIdsUseCase = getProductListByIdsUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
    }

    public void fetchOrder(String orderId) {
        getOrderByIdUseCase.execute(orderId, new Callback<Order>() {
            @Override
            public void onSuccess(Order result) {
                Log.d(TAG, "onSuccess: " + result);
                order.postValue(result);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e(TAG, "onFailure: " + errorMessage);
            }
        });
    }

    public void fetchProductList(List<Integer> productIds) {
        disposable.add(getProductListByIdsUseCase.execute(productIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productList1 -> {
                    Log.d(TAG, "fetchProductList: " + productList1);
                    productList.postValue(productList1);
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
