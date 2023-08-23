package com.sametozkan.storeapp.presentation.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.sametozkan.storeapp.presentation.MainActivity;
import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.domain.model.User;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.presentation.authentication.login.LoginFragment;
import com.sametozkan.storeapp.presentation.authentication.register.RegisterFragment;

import javax.inject.Inject;

public class AuthActivity extends AppCompatActivity implements AuthClickListener {

    private static final String TAG = "AuthActivity";

    @Inject
    ViewModelFactory viewModelFactory;

    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        authViewModel = new ViewModelProvider(this, viewModelFactory).get(AuthViewModel.class);
        openLoginFragment();
    }

    private void openLoginFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new LoginFragment())
                .commit();
    }

    private void openRegisterFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new RegisterFragment())
                .commit();
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginButtonClicked(String email, String password) {
        authViewModel.loginUser(email, password, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Signed in successfully!", Toast.LENGTH_LONG);
                startMainActivity();
            }
        });
    }

    @Override
    public void onRegisterButtonClicked(String fullName, String email, String password) {
        authViewModel.registerUser(email, password, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Signed up successfully!", Toast.LENGTH_LONG).show();
                authViewModel.loginUser(email, password, task1 -> {
                    if (task1.isSuccessful()) {
                        Log.i(TAG, "onRegisterButtonClicked: Signed in successfully!");
                        authViewModel.saveNewUser(
                                new User(
                                        task.getResult().getUser().getUid(),
                                        fullName,
                                        email), task2 -> {
                                    if (task1.isSuccessful()) {
                                        Log.i(TAG, "onRegisterButtonClicked: task is successfull!");
                                        startMainActivity();
                                    } else {
                                        Log.e(TAG, "onRegisterButtonClicked: task is not successfull", task.getException());
                                    }
                                });
                    } else {
                        Log.e(TAG, "onRegisterButtonClicked: ", task1.getException());
                    }
                });
            }
        });
    }

    @Override
    public void onCreateAccountButtonClicked() {
        Log.d(TAG, "onCreateAccountButtonClicked: ");
        openRegisterFragment();
    }
}
