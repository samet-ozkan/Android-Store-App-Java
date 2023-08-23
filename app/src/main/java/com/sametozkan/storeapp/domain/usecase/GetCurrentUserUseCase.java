package com.sametozkan.storeapp.domain.usecase;

import com.google.firebase.auth.FirebaseUser;
import com.sametozkan.storeapp.domain.model.User;
import com.sametozkan.storeapp.domain.repository.FirebaseRepository;
import com.sametozkan.storeapp.util.Callback;

import javax.inject.Inject;

public class GetCurrentUserUseCase {

    private FirebaseRepository firebaseRepository;

    @Inject
    public GetCurrentUserUseCase(FirebaseRepository firebaseRepository) {
        this.firebaseRepository = firebaseRepository;
    }

    public FirebaseUser execute() {
        return firebaseRepository.getCurrentUser();
    }

    public void execute(String uid, Callback<User> callback) {
        firebaseRepository.getCurrentUser(uid, callback);
    }

}
