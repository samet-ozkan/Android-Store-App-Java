package com.sametozkan.storeapp.presentation.category;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.domain.usecase.GetProductsByCategoryUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryViewModel extends ViewModel {

    private static final String TAG = "CategoryViewModel";

    private CompositeDisposable compositeDisposable;
    private GetProductsByCategoryUseCase getProductsByCategoryUseCase;
    public final MutableLiveData<List<Product>> productList = new MutableLiveData<>();

    @Inject
    public CategoryViewModel(GetProductsByCategoryUseCase getProductsByCategoryUseCase){
        compositeDisposable = new CompositeDisposable();
        this.getProductsByCategoryUseCase = getProductsByCategoryUseCase;
    }

    public void fetchProductsByCategory(String category){
        compositeDisposable.add(
                getProductsByCategoryUseCase.execute(category)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(products -> {
                            productList.postValue(products);
                            Log.d(TAG, "fetchProductsByCategory: " + products);
                        }, throwable -> {
                            Log.e(TAG, "fetchProductsByCategory: ", throwable);
                        }));
    }

    public LiveData<List<Product>> getProductList() {
        return productList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
