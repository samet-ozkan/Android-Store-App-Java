package com.sametozkan.storeapp.di.component;

import com.sametozkan.storeapp.MainActivity;
import com.sametozkan.storeapp.di.module.AppModule;
import com.sametozkan.storeapp.di.module.NetworkModule;
import com.sametozkan.storeapp.di.module.RepositoryModule;
import com.sametozkan.storeapp.di.module.ViewModelModule;
import com.sametozkan.storeapp.presentation.category.CategoryActivity;
import com.sametozkan.storeapp.presentation.home.HomeFragment;
import com.sametozkan.storeapp.presentation.product.ProductDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        RepositoryModule.class,
        ViewModelModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(HomeFragment homeFragment);

    void inject(ProductDetailActivity productDetailActivity);

    void inject(CategoryActivity categoryActivity);

}
