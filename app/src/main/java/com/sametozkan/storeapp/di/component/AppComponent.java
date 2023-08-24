package com.sametozkan.storeapp.di.component;

import com.sametozkan.storeapp.di.module.AppModule;
import com.sametozkan.storeapp.di.module.FirebaseModule;
import com.sametozkan.storeapp.di.module.NetworkModule;
import com.sametozkan.storeapp.di.module.RepositoryModule;
import com.sametozkan.storeapp.di.module.ViewModelModule;
import com.sametozkan.storeapp.presentation.MainActivity;
import com.sametozkan.storeapp.presentation.authentication.AuthActivity;
import com.sametozkan.storeapp.presentation.category.CategoryActivity;
import com.sametozkan.storeapp.presentation.categoryList.CategoryListFragment;
import com.sametozkan.storeapp.presentation.home.HomeFragment;
import com.sametozkan.storeapp.presentation.product.ProductDetailActivity;
import com.sametozkan.storeapp.presentation.shoppingcart.ShoppingCartFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        RepositoryModule.class,
        ViewModelModule.class,
        FirebaseModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(AuthActivity authActivity);

    void inject(ProductDetailActivity productDetailActivity);

    void inject(HomeFragment homeFragment);

    void inject(CategoryActivity categoryActivity);

    void inject(CategoryListFragment categoryListFragment);

    void inject(ShoppingCartFragment shoppingCartFragment);

}
