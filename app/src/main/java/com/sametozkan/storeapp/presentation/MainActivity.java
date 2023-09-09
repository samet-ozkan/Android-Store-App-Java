package com.sametozkan.storeapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.ActivityMainBinding;
import com.sametozkan.storeapp.domain.model.User;
import com.sametozkan.storeapp.domain.usecase.GetAllProductsUseCase;
import com.sametozkan.storeapp.presentation.authentication.AuthActivity;
import com.sametozkan.storeapp.presentation.categoryList.CategoryListFragment;
import com.sametozkan.storeapp.presentation.home.HomeFragment;
import com.sametozkan.storeapp.presentation.home.HomeViewModel;
import com.sametozkan.storeapp.presentation.pastorders.PastOrdersFragment;
import com.sametozkan.storeapp.presentation.shoppingcart.ShoppingCartFragment;
import com.sametozkan.storeapp.util.Callback;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setViewModel();
        setNavigationDrawer();
        setNavigationViewHeader();
        initFrameLayout();
    }

    private void initFrameLayout(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
        binding.toolbarTitle.setText("Home");
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel.class);
    }

    private void setNavigationDrawer() {
        binding.toolbar.setNavigationIcon(R.drawable.baseline_dehaze_24);
        binding.toolbar.setNavigationOnClickListener(view -> {
            binding.drawerLayout.open();
        });
        setNavigationItemClickListener();
    }

    private void setNavigationItemClickListener() {
        binding.navigationView.setNavigationItemSelectedListener(item -> {
            final int itemId = item.getItemId();
            if (itemId == R.id.home) {
                replace(new HomeFragment(), "Home");
            } else if (itemId == R.id.categories) {
                replace(new CategoryListFragment(), "Categories");
            } else if (itemId == R.id.shoppingCart) {
                replace(new ShoppingCartFragment(), "Shopping Cart");
            } else if (itemId == R.id.pastOrders) {
                replace(new PastOrdersFragment(), "Past Orders");
            }
            else if(itemId == R.id.logout){
                logout();
            }
            else {
                return false;
            }
            return true;
        });
    }

    private void logout(){
        viewModel.logout();
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

    private void replace(Fragment fragment, String title){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
        binding.toolbarTitle.setText(title);
        binding.drawerLayout.closeDrawers();
    }

    private void setNavigationViewHeader() {
        FirebaseUser user = viewModel.getCurrentUser();
        if (user == null)
            Log.e(TAG, "setNavigationViewHeader: ", new NullPointerException());
        else {
            viewModel.getCurrentUser(user.getUid(), new Callback<User>() {
                @Override
                public void onSuccess(User result) {
                    Log.i(TAG, "onSuccess: Fetched user successfully!" + result.getFullName() +
                            result.getEmail());
                    binding.setUser(result);
                }

                @Override
                public void onFailure(String errorMessage) {
                    Log.e(TAG, "onFailure: " + errorMessage);
                }
            });
        }
    }
}




