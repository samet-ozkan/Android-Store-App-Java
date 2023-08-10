package com.sametozkan.storeapp.presentation;

import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.domain.usecase.GetAllProductsUseCase;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private GetAllProductsUseCase getAllProductsUseCase;

    @Inject
    public HomeViewModel(GetAllProductsUseCase getAllProductsUseCase){
        this.getAllProductsUseCase = getAllProductsUseCase;
    }
}
