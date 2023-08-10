package com.sametozkan.storeapp.di.module;

import android.app.Application;

import com.sametozkan.storeapp.data.datasource.remote.ApiService;
import com.sametozkan.storeapp.data.repository.CategoryRepositoryImpl;
import com.sametozkan.storeapp.data.repository.ProductRepositoryImpl;
import com.sametozkan.storeapp.domain.repository.CategoryRepository;
import com.sametozkan.storeapp.domain.repository.ProductRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ProductRepository provideProductRepository(ApiService apiService) {
        return new ProductRepositoryImpl(apiService);
    }

    @Provides
    @Singleton
    public CategoryRepository provideCategoryRepository(ApiService apiService) {
        return new CategoryRepositoryImpl(apiService);
    }
}
