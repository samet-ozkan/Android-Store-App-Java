package com.sametozkan.storeapp.di.module;

import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {

    @Provides
    public FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }
}
