package com.sametozkan.storeapp.presentation.authentication;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.sametozkan.storeapp.domain.model.User;
import com.sametozkan.storeapp.domain.usecase.GetCurrentUserUseCase;
import com.sametozkan.storeapp.domain.usecase.LoginUserUseCase;
import com.sametozkan.storeapp.domain.usecase.RegisterUserUseCase;
import com.sametozkan.storeapp.domain.usecase.SaveNewUserUseCase;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private LoginUserUseCase loginUserUseCase;
    private RegisterUserUseCase registerUserUseCase;
    private SaveNewUserUseCase saveNewUserUseCase;

    private GetCurrentUserUseCase getCurrentUserUseCase;

    @Inject
    public AuthViewModel(LoginUserUseCase loginUserUseCase, RegisterUserUseCase registerUserUseCase,
                         SaveNewUserUseCase saveNewUserUseCase, GetCurrentUserUseCase getCurrentUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
        this.registerUserUseCase = registerUserUseCase;
        this.saveNewUserUseCase = saveNewUserUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
    }

    public FirebaseUser getCurrentUser(){
        return getCurrentUserUseCase.execute();
    }

    public void loginUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        loginUserUseCase.execute(email, password, onCompleteListener);
    }

    public void registerUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        registerUserUseCase.execute(email, password, onCompleteListener);
    }

    public void saveNewUser(User user, OnCompleteListener<Void> onCompleteListener){
        saveNewUserUseCase.execute(user, onCompleteListener);
    }
}
