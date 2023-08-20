package com.sametozkan.storeapp.presentation.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.model.Rating;
import com.sametozkan.storeapp.domain.usecase.GetAllProductsUseCase;
import com.sametozkan.storeapp.domain.usecase.GetLimitedProductsByCategoryUseCase;
import com.sametozkan.storeapp.domain.usecase.GetProductsByCategoryUseCase;
import com.sametozkan.storeapp.util.Categories;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";

    private GetLimitedProductsByCategoryUseCase getLimitedProductsByCategoryUseCase;
    private final MutableLiveData<List<Product>> mensClothingList = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> womensClothingList = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> jeweleryList = new MutableLiveData<>();
    private MutableLiveData<List<Product>> electronicsList = new MutableLiveData<>();
    private CompositeDisposable disposable;

    @Inject
    public HomeViewModel(GetLimitedProductsByCategoryUseCase getLimitedProductsByCategoryUseCase) {
        this.getLimitedProductsByCategoryUseCase = getLimitedProductsByCategoryUseCase;
        disposable = new CompositeDisposable();
        fetchLimitedProductsByCategoryUseCase();
    }

    private void fetchLimitedProductsByCategoryUseCase() {
        final int count = 5;
        disposable.addAll(
                //Men's Clothing
                getLimitedProductsByCategoryUseCase.execute(Categories.MENS_CLOTHING.category, count)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(products -> {
                            mensClothingList.postValue(products);
                            Log.d(TAG, "fetchLimitedProductsByCategoryUseCase: " + products.get(1).getRating().getRate()
                            );
                        }, throwable -> {
                            Log.e(TAG, "fetchLimitedProductsByCategoryUseCase: ", throwable);
                        }),
                //Women's Clothing
                getLimitedProductsByCategoryUseCase.execute(Categories.WOMENS_CLOTHING.category, count)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(products -> {
                            womensClothingList.postValue(products);
                        }, throwable -> {
                            Log.e(TAG, "fetchLimitedProductsByCategoryUseCase: ", throwable);
                        }),
                //Jewelery
                getLimitedProductsByCategoryUseCase.execute(Categories.JEWELERY.category, count)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(products -> {
                            jeweleryList.postValue(products);
                        }, throwable -> {
                            Log.e(TAG, "fetchLimitedProductsByCategoryUseCase: ", throwable);
                        }),
                //Electronics
                getLimitedProductsByCategoryUseCase.execute(Categories.ELECTRONICS.category, count)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(products -> {
                            electronicsList.postValue(products);
                        }, throwable -> {
                            Log.e(TAG, "fetchLimitedProductsByCategoryUseCase: ", throwable);
                        })
        );

    }

    public LiveData<List<Product>> getMensClothingList() {
        return mensClothingList;
    }

    public LiveData<List<Product>> getWomensClothingList() {
        return womensClothingList;
    }

    public LiveData<List<Product>> getJeweleryList() {
        return jeweleryList;
    }

    public LiveData<List<Product>> getElectronicsList() {
        return electronicsList;
    }

    public CompositeDisposable getDisposable() {
        return disposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
