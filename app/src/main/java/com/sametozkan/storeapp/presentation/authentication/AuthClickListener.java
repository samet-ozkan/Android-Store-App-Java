package com.sametozkan.storeapp.presentation.authentication;

public interface AuthClickListener {
    void onLoginButtonClicked(String email, String password);

    void onRegisterButtonClicked(String fullName, String email, String password);

    void onCreateAccountButtonClicked();

    void onLoginButtonClicked();
}
