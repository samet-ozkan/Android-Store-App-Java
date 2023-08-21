package com.sametozkan.storeapp.domain.usecase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.sametozkan.storeapp.domain.repository.FirebaseRepository;

import javax.inject.Inject;

public class RegisterUserUseCase {

    FirebaseRepository firebaseRepository;

    @Inject
    public RegisterUserUseCase(FirebaseRepository firebaseRepository){
        this.firebaseRepository = firebaseRepository;
    }

    public void execute(String email, String password, OnCompleteListener<AuthResult> onCompleteListener){
        firebaseRepository.registerUser(email, password, onCompleteListener);
    }
}
