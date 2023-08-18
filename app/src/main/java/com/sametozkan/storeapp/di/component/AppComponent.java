package com.sametozkan.storeapp.di.component;

import com.sametozkan.storeapp.MainActivity;
import com.sametozkan.storeapp.di.module.AppModule;
import com.sametozkan.storeapp.di.module.NetworkModule;
import com.sametozkan.storeapp.di.module.RepositoryModule;
import com.sametozkan.storeapp.di.module.ViewModelModule;
import com.sametozkan.storeapp.domain.repository.CategoryRepository;
import com.sametozkan.storeapp.domain.repository.ProductRepository;
import com.sametozkan.storeapp.presentation.home.HomeFragment;

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
}
