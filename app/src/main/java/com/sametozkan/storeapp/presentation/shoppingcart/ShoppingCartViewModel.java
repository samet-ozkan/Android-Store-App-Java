package com.sametozkan.storeapp.presentation.shoppingcart;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.data.datasource.local.sharedpreferences.ShoppingCart;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.usecase.GetCurrentUserUseCase;
import com.sametozkan.storeapp.domain.usecase.GetProductByIdUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ShoppingCartViewModel extends ViewModel {

    private static final String TAG = "ShoppingCartViewModel";

    private CompositeDisposable compositeDisposable;
    private GetProductByIdUseCase getProductByIdUseCase;
    private GetCurrentUserUseCase getCurrentUserUseCase;

    private final MutableLiveData<List<Product>> productList = new MutableLiveData<>();

    public LiveData<List<Product>> getProductList() {
        return productList;
    }

    @Inject
    public ShoppingCartViewModel(GetProductByIdUseCase getProductByIdUseCase, GetCurrentUserUseCase getCurrentUserUseCase) {
        this.compositeDisposable = new CompositeDisposable();
        this.getCurrentUserUseCase = getCurrentUserUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
    }

    private String getCurrentUserEmail() {
        return getCurrentUserUseCase.execute().getEmail();
    }

    public void fetchCartItems(Context context) {
        final ShoppingCart shoppingCart = new ShoppingCart(context, getCurrentUserEmail());
        compositeDisposable.add(Observable.fromIterable(shoppingCart.getCartItems())
                .flatMapSingle(productId -> getProductByIdUseCase.execute(Integer.valueOf(productId)))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> {
                    productList.postValue(products);
                    Log.d(TAG, "fetchCartItems: " + products);
                }));
    }

    public void removeCartItem(Context context, int productId) {
        final ShoppingCart shoppingCart = new ShoppingCart(context, getCurrentUserEmail());
        shoppingCart.removeProductIdFromCart(productId);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
