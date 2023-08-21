package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.domain.repository.FirebaseRepository;

import javax.inject.Inject;

public class LogoutUserUseCase {

    FirebaseRepository firebaseRepository;

    @Inject
    public LogoutUserUseCase(FirebaseRepository firebaseRepository) {
        this.firebaseRepository = firebaseRepository;
    }

    public void execute() {
        firebaseRepository.logoutUser();
    }
}
