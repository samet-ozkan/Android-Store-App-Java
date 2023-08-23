package com.sametozkan.storeapp.domain.usecase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.sametozkan.storeapp.domain.model.User;
import com.sametozkan.storeapp.domain.repository.FirebaseRepository;

import javax.inject.Inject;

public class SaveNewUserUseCase {

    private FirebaseRepository firebaseRepository;

    @Inject
    public SaveNewUserUseCase(FirebaseRepository firebaseRepository){
        this.firebaseRepository = firebaseRepository;
    }

    public void execute(User user, OnCompleteListener<Void> onCompleteListener){
        firebaseRepository.saveNewUser(user, onCompleteListener);
    }
}
