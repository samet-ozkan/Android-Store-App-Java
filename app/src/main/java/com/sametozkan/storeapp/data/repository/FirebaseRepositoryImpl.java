package com.sametozkan.storeapp.data.repository;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sametozkan.storeapp.domain.repository.FirebaseRepository;

import javax.inject.Inject;

public class FirebaseRepositoryImpl implements FirebaseRepository {

    private FirebaseAuth firebaseAuth;

    @Inject
    public FirebaseRepositoryImpl(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public void registerUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener);
    }

    @Override
    public void loginUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener);
    }

    @Override
    public void logoutUser() {
        firebaseAuth.signOut();
    }
}
