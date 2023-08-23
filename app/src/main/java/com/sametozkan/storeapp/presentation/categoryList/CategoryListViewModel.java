package com.sametozkan.storeapp.presentation.categoryList;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.domain.usecase.GetCategoryListUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryListViewModel extends ViewModel {

    private static final String TAG = "CategoryListViewModel";

    private CompositeDisposable disposable;
    private GetCategoryListUseCase getCategoryListUseCase;
    private MutableLiveData<List<String>> categoryList = new MutableLiveData<>();

    @Inject
    public CategoryListViewModel(GetCategoryListUseCase getCategoryListUseCase) {
        disposable = new CompositeDisposable();
        this.getCategoryListUseCase = getCategoryListUseCase;
        fetchCategoryListUseCase();
    }

    public void fetchCategoryListUseCase() {
        disposable.add(getCategoryListUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((strings, throwable) -> {
                    categoryList.postValue(strings);
                    if (throwable != null)
                        Log.e(TAG, "fetchCategoryListUseCase: ", throwable);
                }));
    }

    public LiveData<List<String>> getCategoryList() {
        return categoryList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
