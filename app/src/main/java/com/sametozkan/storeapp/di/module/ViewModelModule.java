package com.sametozkan.storeapp.di.module;

import androidx.lifecycle.ViewModel;

import com.sametozkan.storeapp.domain.usecase.ConfirmOrderUseCase;
import com.sametozkan.storeapp.domain.usecase.GetCategoryListUseCase;
import com.sametozkan.storeapp.domain.usecase.GetCurrentUserUseCase;
import com.sametozkan.storeapp.domain.usecase.GetLimitedProductsByCategoryUseCase;
import com.sametozkan.storeapp.domain.usecase.GetOrderByIdUseCase;
import com.sametozkan.storeapp.domain.usecase.GetPastOrdersUseCase;
import com.sametozkan.storeapp.domain.usecase.GetProductByIdUseCase;
import com.sametozkan.storeapp.domain.usecase.GetProductListByIdsUseCase;
import com.sametozkan.storeapp.domain.usecase.GetProductsByCategoryUseCase;
import com.sametozkan.storeapp.domain.usecase.LoginUserUseCase;
import com.sametozkan.storeapp.domain.usecase.RegisterUserUseCase;
import com.sametozkan.storeapp.domain.usecase.SaveNewUserUseCase;
import com.sametozkan.storeapp.presentation.MainActivityViewModel;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.presentation.authentication.AuthViewModel;
import com.sametozkan.storeapp.presentation.category.CategoryViewModel;
import com.sametozkan.storeapp.presentation.categoryList.CategoryListViewModel;
import com.sametozkan.storeapp.presentation.home.HomeViewModel;
import com.sametozkan.storeapp.presentation.orderdetail.OrderDetailViewModel;
import com.sametozkan.storeapp.presentation.pastorders.PastOrdersViewModel;
import com.sametozkan.storeapp.presentation.product.ProductDetailViewModel;
import com.sametozkan.storeapp.presentation.shoppingcart.ShoppingCartViewModel;

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
            Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    ViewModel provideHomeViewModel(GetLimitedProductsByCategoryUseCase getLimitedProductsByCategoryUseCase) {
        return new HomeViewModel(getLimitedProductsByCategoryUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(ProductDetailViewModel.class)
    ViewModel provideProductDetailViewModel(GetProductByIdUseCase getProductByIdUseCase,
                                            GetCurrentUserUseCase getCurrentUserUseCase) {
        return new ProductDetailViewModel(getProductByIdUseCase, getCurrentUserUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(CategoryViewModel.class)
    ViewModel provideCategoryViewModel(GetProductsByCategoryUseCase getProductsByCategoryUseCase) {
        return new CategoryViewModel(getProductsByCategoryUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    ViewModel provideAuthViewModel(LoginUserUseCase loginUserUseCase, RegisterUserUseCase registerUserUseCase,
                                   SaveNewUserUseCase saveNewUserUseCase) {
        return new AuthViewModel(loginUserUseCase, registerUserUseCase, saveNewUserUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(CategoryListViewModel.class)
    ViewModel provideCategoryListViewModel(GetCategoryListUseCase getCategoryListUseCase) {
        return new CategoryListViewModel(getCategoryListUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    ViewModel provideMainActivityViewModel(GetCurrentUserUseCase getCurrentUserUseCase) {
        return new MainActivityViewModel(getCurrentUserUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(ShoppingCartViewModel.class)
    ViewModel provideShoppingCartViewModel(
            GetProductByIdUseCase getProductByIdUseCase,
            GetCurrentUserUseCase getCurrentUserUseCase,
            ConfirmOrderUseCase confirmOrderUseCase) {
        return new ShoppingCartViewModel(
                getProductByIdUseCase,
                getCurrentUserUseCase,
                confirmOrderUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(PastOrdersViewModel.class)
    ViewModel providePastOrdersViewModel(GetPastOrdersUseCase getPastOrdersUseCase) {
        return new PastOrdersViewModel(getPastOrdersUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(OrderDetailViewModel.class)
    ViewModel provideOrderDetailViewModel(GetOrderByIdUseCase getOrderByIdUseCase,
                                          GetProductListByIdsUseCase getProductListByIdsUseCase){
        return new OrderDetailViewModel(getOrderByIdUseCase, getProductListByIdsUseCase);
    }
}
