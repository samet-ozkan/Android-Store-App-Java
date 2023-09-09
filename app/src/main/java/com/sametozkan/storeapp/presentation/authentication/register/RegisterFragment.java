package com.sametozkan.storeapp.presentation.authentication.register;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.FragmentRegisterBinding;
import com.sametozkan.storeapp.presentation.authentication.AuthClickListener;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private AuthClickListener authClickListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            authClickListener = (AuthClickListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        binding.setAuthClickListener(authClickListener);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRegisterButtonClickListener();
        loginButtonClickListener();
    }

    private void setRegisterButtonClickListener() {
        binding.registerButton.setOnClickListener(view -> {
            authClickListener.onRegisterButtonClicked(
                    binding.fullName.getText().toString(),
                    binding.email.getText().toString(),
                    binding.password.getText().toString());
        });
    }

    private void loginButtonClickListener(){
        binding.login.setOnClickListener(view -> {
            authClickListener.onLoginButtonClicked();
        });
    }


}
