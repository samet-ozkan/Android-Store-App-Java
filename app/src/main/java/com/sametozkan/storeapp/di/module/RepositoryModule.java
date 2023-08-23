package com.sametozkan.storeapp.di.module;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sametozkan.storeapp.data.datasource.remote.ApiService;
import com.sametozkan.storeapp.data.repository.CategoryRepositoryImpl;
import com.sametozkan.storeapp.data.repository.FirebaseRepositoryImpl;
import com.sametozkan.storeapp.data.repository.ProductRepositoryImpl;
import com.sametozkan.storeapp.domain.repository.CategoryRepository;
import com.sametozkan.storeapp.domain.repository.FirebaseRepository;
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

    @Provides
    @Singleton
    public FirebaseRepository provideFirebaseRepository(FirebaseAuth firebaseAuth,
                                                        FirebaseFirestore firebaseFirestore){
        return new FirebaseRepositoryImpl(firebaseAuth, firebaseFirestore);
    }
}
