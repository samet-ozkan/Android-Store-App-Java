package com.sametozkan.storeapp.presentation.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.usecase.GetLimitedProductsByCategoryUseCase;
import com.sametozkan.storeapp.util.Categories;
import com.sametozkan.storeapp.util.States;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";

    private GetLimitedProductsByCategoryUseCase getLimitedProductsByCategoryUseCase;

    private final Map<Categories, MutableLiveData<List<Product>>> categoryMap = new HashMap<>();
    private final Map<Categories, MutableLiveData<States>> stateMap = new HashMap<>();

    private CompositeDisposable disposable;

    @Inject
    public HomeViewModel(GetLimitedProductsByCategoryUseCase getLimitedProductsByCategoryUseCase) {
        this.getLimitedProductsByCategoryUseCase = getLimitedProductsByCategoryUseCase;
        disposable = new CompositeDisposable();
        for (Categories category : Categories.values()) {
            categoryMap.put(category, new MutableLiveData<>());
            stateMap.put(category, new MutableLiveData<>(States.LOADING));
            fetchLimitedProductsByCategory(category, 5);
        }

    }

    private void fetchLimitedProductsByCategory(Categories category, int count) {
        disposable.addAll(
                getLimitedProductsByCategoryUseCase.execute(category.category, count)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(products -> {
                            categoryMap.get(category).postValue(products);
                            if (products.isEmpty())
                                stateMap.get(category).postValue(States.EMPTY);
                            else
                                stateMap.get(category).postValue(States.SUCCESS);
                        }, throwable -> {
                            if (throwable != null)
                                stateMap.get(category).postValue(States.ERROR);
                        })
        );
    }

    public LiveData<List<Product>> getProductList(Categories category) {
        return categoryMap.get(category);
    }

    public LiveData<States> getState(Categories category) {
        return stateMap.get(category);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
