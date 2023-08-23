package com.sametozkan.storeapp.domain.repository;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.sametozkan.storeapp.data.dto.UserDTO;
import com.sametozkan.storeapp.domain.model.User;
import com.sametozkan.storeapp.util.Callback;

public interface FirebaseRepository {

    void registerUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener);

    void loginUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener);

    void logoutUser();

    void saveNewUser(User user, OnCompleteListener<Void> onCompleteListener);

    FirebaseUser getCurrentUser();

    void getCurrentUser(String uid, Callback<User> callback);
}
