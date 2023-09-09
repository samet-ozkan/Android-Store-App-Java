package com.sametozkan.storeapp.presentation.categoryList;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.data.mapper.CategoryListMapper;
import com.sametozkan.storeapp.domain.usecase.GetCategoryListUseCase;
import com.sametozkan.storeapp.util.Categories;
import com.sametozkan.storeapp.util.States;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryListViewModel extends ViewModel {

    private static final String TAG = "CategoryListViewModel";

    private CompositeDisposable disposable;
    private GetCategoryListUseCase getCategoryListUseCase;
    private MutableLiveData<List<Categories>> categoryList = new MutableLiveData<>();
    private MutableLiveData<States> state = new MutableLiveData<>(States.LOADING);

    @Inject
    public CategoryListViewModel(GetCategoryListUseCase getCategoryListUseCase) {
        disposable = new CompositeDisposable();
        this.getCategoryListUseCase = getCategoryListUseCase;
        fetchCategoryListUseCase();
    }

    public void fetchCategoryListUseCase() {
        disposable.add(getCategoryListUseCase.execute()
                        .map(CategoryListMapper::toCategoryList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((categories, throwable) -> {
                    Log.d(TAG, "fetchCategoryListUseCase: " + categories);
                    categoryList.postValue(categories);
                    state.postValue(States.SUCCESS);
                    if (throwable != null)
                        Log.e(TAG, "fetchCategoryListUseCase: ", throwable);
                }));
    }

    public LiveData<List<Categories>> getCategoryList() {
        return categoryList;
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
