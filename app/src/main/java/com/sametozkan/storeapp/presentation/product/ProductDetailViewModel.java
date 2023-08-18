package com.sametozkan.storeapp.presentation.product;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductDetailViewModel extends ViewModel {

    private final MutableLiveData<Long> productId = new MutableLiveData<>();
}
