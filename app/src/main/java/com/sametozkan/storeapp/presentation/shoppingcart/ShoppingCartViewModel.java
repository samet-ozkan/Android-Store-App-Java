package com.sametozkan.storeapp.presentation.shoppingcart;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.data.datasource.local.sharedpreferences.ShoppingCart;
import com.sametozkan.storeapp.domain.model.Order;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.usecase.ConfirmOrderUseCase;
import com.sametozkan.storeapp.domain.usecase.GetCurrentUserUseCase;
import com.sametozkan.storeapp.domain.usecase.GetProductByIdUseCase;
import com.sametozkan.storeapp.util.Callback;
import com.sametozkan.storeapp.util.Utils;

import java.util.List;
import java.util.stream.Collectors;

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

    private ConfirmOrderUseCase confirmOrderUseCase;
    private final MutableLiveData<List<Product>> productList = new MutableLiveData<>();
    private final MutableLiveData<Double> totalAmount = new MutableLiveData<>(0d);

    public LiveData<List<Product>> getProductList() {
        return productList;
    }

    public LiveData<Double> getTotalAmount() {
        return totalAmount;
    }

    @Inject
    public ShoppingCartViewModel(
            GetProductByIdUseCase getProductByIdUseCase,
            GetCurrentUserUseCase getCurrentUserUseCase,
            ConfirmOrderUseCase confirmOrderUseCase) {
        this.compositeDisposable = new CompositeDisposable();
        this.getCurrentUserUseCase = getCurrentUserUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.confirmOrderUseCase = confirmOrderUseCase;
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

    public void calculateTotalAmount(List<Product> productList) {
        double totalAmount = 0;
        for (Product product : productList) {
            totalAmount += product.getPrice();
        }
        this.totalAmount.postValue(totalAmount);
    }

    public void clearCart(Context context) {
        final ShoppingCart shoppingCart = new ShoppingCart(context, getCurrentUserEmail());
        shoppingCart.clearCart();
        totalAmount.postValue(0d);
    }

    public void confirmOrder(Callback<Void> callback) {
        List<Integer> productIds = productList.getValue().stream().map(product -> product.getId())
                .collect(Collectors.toList());
        Order order = new Order(Utils.generateRandomId(), productIds, totalAmount.getValue(),
                Utils.convertTimestampToDate(Utils.getTimestamp()));
        confirmOrderUseCase.execute(order, callback);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
