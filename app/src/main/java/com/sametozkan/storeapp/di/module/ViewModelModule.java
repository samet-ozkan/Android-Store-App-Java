package com.sametozkan.storeapp.di.module;

import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.domain.usecase.GetLimitedProductsByCategoryUseCase;
import com.sametozkan.storeapp.domain.usecase.GetProductByIdUseCase;
import com.sametozkan.storeapp.domain.usecase.GetProductsByCategoryUseCase;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.presentation.category.CategoryViewModel;
import com.sametozkan.storeapp.presentation.home.HomeViewModel;
import com.sametozkan.storeapp.presentation.product.ProductDetailViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory provideViewModelFactory(Map<Class<? extends ViewModel>,
            Provider<ViewModel>> providerMap){
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    ViewModel provideHomeViewModel(GetLimitedProductsByCategoryUseCase getLimitedProductsByCategoryUseCase){
        return new HomeViewModel(getLimitedProductsByCategoryUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(ProductDetailViewModel.class)
    ViewModel provideProductDetailViewModel(GetProductByIdUseCase getProductByIdUseCase){
        return new ProductDetailViewModel(getProductByIdUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(CategoryViewModel.class)
    ViewModel provideCategoryViewModel(GetProductsByCategoryUseCase getProductsByCategoryUseCase){
        return new CategoryViewModel(getProductsByCategoryUseCase);
    }
}
