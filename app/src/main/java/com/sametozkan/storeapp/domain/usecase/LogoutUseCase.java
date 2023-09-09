package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.domain.repository.FirebaseRepository;

import javax.inject.Inject;

public class LogoutUseCase {

    private FirebaseRepository firebaseRepository;

    @Inject
    public LogoutUseCase(FirebaseRepository firebaseRepository){
        this.firebaseRepository = firebaseRepository;
    }

    public void execute(){
        firebaseRepository.logoutUser();
    }
}
