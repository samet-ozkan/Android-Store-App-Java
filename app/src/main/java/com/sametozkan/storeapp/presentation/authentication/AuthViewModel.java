package com.sametozkan.storeapp.presentation.authentication;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.sametozkan.storeapp.domain.usecase.LoginUserUseCase;
import com.sametozkan.storeapp.domain.usecase.RegisterUserUseCase;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private LoginUserUseCase loginUserUseCase;
    private RegisterUserUseCase registerUserUseCase;

    @Inject
    public AuthViewModel(LoginUserUseCase loginUserUseCase, RegisterUserUseCase registerUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
        this.registerUserUseCase = registerUserUseCase;
    }

    public void loginUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        loginUserUseCase.execute(email, password, onCompleteListener);
    }

    public void registerUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        registerUserUseCase.execute(email, password, onCompleteListener);
    }
}
