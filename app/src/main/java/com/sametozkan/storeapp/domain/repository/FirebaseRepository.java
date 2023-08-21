package com.sametozkan.storeapp.domain.repository;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

public interface FirebaseRepository {

    void registerUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener);

    void loginUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener);

    void logoutUser();
}
