package com.sametozkan.storeapp.presentation.authentication.login;

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
import com.sametozkan.storeapp.databinding.FragmentLoginBinding;
import com.sametozkan.storeapp.presentation.authentication.AuthClickListener;
import com.sametozkan.storeapp.presentation.authentication.register.RegisterFragment;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLoginButtonClickListener();
        setCreateAccountButtonClickListener();
    }

    private void setLoginButtonClickListener() {
        binding.loginButton.setOnClickListener(view -> {
            authClickListener.onLoginButtonClicked(
                    binding.email.getText().toString(),
                    binding.password.getText().toString()
            );
        });
    }

    private void setCreateAccountButtonClickListener() {
        binding.register.setOnClickListener(view -> {
            authClickListener.onCreateAccountButtonClicked();
        });
    }
}
