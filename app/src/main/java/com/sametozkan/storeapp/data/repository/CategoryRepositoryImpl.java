package com.sametozkan.storeapp.data.repository;

import android.app.Application;

import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.data.datasource.remote.ApiService;
import com.sametozkan.storeapp.data.mapper.CategoryListMapper;
import com.sametozkan.storeapp.domain.repository.CategoryRepository;
import com.sametozkan.storeapp.util.Categories;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class CategoryRepositoryImpl implements CategoryRepository {

    ApiService apiService;

    @Inject
    public CategoryRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<String>> getCategoryList() {
        return apiService.getCategoryList();
    }
}
