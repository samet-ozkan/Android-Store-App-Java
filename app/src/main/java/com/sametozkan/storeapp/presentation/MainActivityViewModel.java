package com.sametozkan.storeapp.presentation;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.sametozkan.storeapp.domain.model.User;
import com.sametozkan.storeapp.domain.usecase.GetCurrentUserUseCase;
import com.sametozkan.storeapp.domain.usecase.LogoutUseCase;
import com.sametozkan.storeapp.util.Callback;

import javax.inject.Inject;

public class MainActivityViewModel extends ViewModel {

    private GetCurrentUserUseCase getCurrentUserUseCase;

    private LogoutUseCase logoutUseCase;

    @Inject
    public MainActivityViewModel(GetCurrentUserUseCase getCurrentUserUseCase, LogoutUseCase logoutUseCase) {
        this.getCurrentUserUseCase = getCurrentUserUseCase;
        this.logoutUseCase = logoutUseCase;
    }


    public void logout() {
        logoutUseCase.execute();
    }

    public FirebaseUser getCurrentUser() {
        return getCurrentUserUseCase.execute();
    }

    public void getCurrentUser(String uid, Callback<User> callback) {
        getCurrentUserUseCase.execute(uid, callback);
    }
}
