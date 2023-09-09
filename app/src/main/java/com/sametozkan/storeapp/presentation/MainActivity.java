package com.sametozkan.storeapp.presentation;

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

    private ActionBarDrawerToggle actionBarDrawerToggle;
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

    /*private void setToolbar() {
        setSupportActionBar(binding.toolbar);
    }*/

    private void setNavigationDrawer() {
        binding.toolbar.setNavigationIcon(R.drawable.baseline_dehaze_24);
        /*actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open_drawer, R.string.close_drawer);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);*/
        binding.toolbar.setNavigationOnClickListener(view -> {
            binding.drawerLayout.open();
        });
        setNavigationItemClickListener();
    }

    private void setNavigationItemClickListener() {
        binding.navigationView.setNavigationItemSelectedListener(item -> {
            final int itemId = item.getItemId();
            Fragment fragment;
            String title;
            if (itemId == R.id.home) {
                fragment = new HomeFragment();
                title = "Home";
                Log.d(TAG, "onCreate: Home clicked!");
            } else if (itemId == R.id.categories) {
                fragment = new CategoryListFragment();
                title = "Categories";
                Log.d(TAG, "onCreate: Categories clicked!");
            } else if (itemId == R.id.shoppingCart) {
                fragment = new ShoppingCartFragment();
                title = "Shopping Cart";
                Log.d(TAG, "setNavigationItemClickListener: ShoppingCart clicked!");
            } else if (itemId == R.id.pastOrders) {
                fragment = new PastOrdersFragment();
                title = "Past Orders";
                Log.d(TAG, "setNavigationItemClickListener: PastOrders clicked!");
            } else {
                return false;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            binding.toolbarTitle.setText(title);
            binding.drawerLayout.closeDrawers();
            return true;
        });
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




