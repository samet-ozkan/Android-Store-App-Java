package com.sametozkan.storeapp.domain.repository;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.sametozkan.storeapp.data.dto.UserDTO;
import com.sametozkan.storeapp.domain.model.Order;
import com.sametozkan.storeapp.domain.model.User;
import com.sametozkan.storeapp.util.Callback;

import java.util.List;

public interface FirebaseRepository {

    void registerUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener);

    void loginUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener);

    void logoutUser();

    void saveNewUser(User user, OnCompleteListener<Void> onCompleteListener);

    void getCurrentUser(String uid, Callback<User> callback);

    void confirmOrder(Order order, Callback<Void> callback);

    void getPastOrders(Callback<List<Order>> callback);
    void getOrderById(String orderId, Callback<Order> callback);

    FirebaseUser getCurrentUser();

}
