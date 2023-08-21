package com.sametozkan.storeapp.domain.usecase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.sametozkan.storeapp.domain.repository.FirebaseRepository;

import javax.inject.Inject;

public class LoginUserUseCase {

    FirebaseRepository firebaseRepository;

    @Inject
    public LoginUserUseCase(FirebaseRepository firebaseRepository){
        this.firebaseRepository = firebaseRepository;
    }

    public void execute(String email, String password, OnCompleteListener<AuthResult> onCompleteListener){
        firebaseRepository.loginUser(email, password, onCompleteListener);
    }
}
