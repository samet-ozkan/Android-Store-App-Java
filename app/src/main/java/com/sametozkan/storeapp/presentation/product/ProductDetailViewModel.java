package com.sametozkan.storeapp.presentation.product;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.usecase.GetCurrentUserUseCase;
import com.sametozkan.storeapp.domain.usecase.GetProductByIdUseCase;
import com.sametozkan.storeapp.util.States;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProductDetailViewModel extends ViewModel {

    private static final String TAG = "ProductDetailViewModel";
    private final MutableLiveData<Integer> productId = new MutableLiveData<>();

    private final MutableLiveData<Product> product = new MutableLiveData<Product>();
    private CompositeDisposable disposable;
    private GetProductByIdUseCase getProductByIdUseCase;
    private GetCurrentUserUseCase getCurrentUserUseCase;
    private final MutableLiveData<States> state = new MutableLiveData<>(States.LOADING);

    @Inject
    public ProductDetailViewModel(GetProductByIdUseCase getProductByIdUseCase,
                                  GetCurrentUserUseCase getCurrentUserUseCase) {
        disposable = new CompositeDisposable();
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
    }

    public FirebaseUser getCurrentUser() {
        return getCurrentUserUseCase.execute();
    }

    public MutableLiveData<Integer> getProductId() {
        return productId;
    }

    public void fetchProductById(int productId) {
        disposable.add(getProductByIdUseCase.execute(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((product1) -> {
                    this.product.postValue(product1);
                    state.postValue(States.SUCCESS);
                }));
    }

    public LiveData<Product> getProduct() {
        return product;
    }

    public LiveData<States> getState() {
        return state;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
