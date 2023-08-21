package com.sametozkan.storeapp.di.component;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sametozkan.storeapp.MainActivity;
import com.sametozkan.storeapp.di.module.AppModule;
import com.sametozkan.storeapp.di.module.FirebaseModule;
import com.sametozkan.storeapp.di.module.NetworkModule;
import com.sametozkan.storeapp.di.module.RepositoryModule;
import com.sametozkan.storeapp.di.module.ViewModelModule;
import com.sametozkan.storeapp.presentation.authentication.AuthActivity;
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
        ViewModelModule.class,
        FirebaseModule.class})
public interface AppComponent {

    void inject(AuthActivity authActivity);
    void inject(ProductDetailActivity productDetailActivity);
    void inject(HomeFragment homeFragment);

    void inject(CategoryActivity categoryActivity);

}
