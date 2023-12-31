package com.sametozkan.storeapp.data.repository;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.sametozkan.storeapp.data.dto.OrderDTO;
import com.sametozkan.storeapp.data.dto.UserDTO;
import com.sametozkan.storeapp.data.mapper.OrderMapper;
import com.sametozkan.storeapp.data.mapper.UserMapper;
import com.sametozkan.storeapp.domain.model.Order;
import com.sametozkan.storeapp.domain.model.User;
import com.sametozkan.storeapp.domain.repository.FirebaseRepository;
import com.sametozkan.storeapp.util.Callback;
import com.sametozkan.storeapp.util.FirebaseConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FirebaseRepositoryImpl implements FirebaseRepository {

    private static final String TAG = "FirebaseRepositoryImpl";

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Inject
    public FirebaseRepositoryImpl(FirebaseAuth firebaseAuth, FirebaseFirestore firebaseFirestore) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseFirestore = firebaseFirestore;
    }

    @Override
    public void registerUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener);
    }

    @Override
    public void loginUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener);
    }

    @Override
    public void logoutUser() {
        firebaseAuth.signOut();
    }

    @Override
    public void saveNewUser(User user, OnCompleteListener<Void> onCompleteListener) {
        UserDTO userDTO = UserMapper.toUserDTO(user);
        firebaseFirestore.collection(FirebaseConstants.USERS)
                .document(String.valueOf(userDTO.getUid()))
                .set(userDTO)
                .addOnCompleteListener(onCompleteListener);
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }

    @Override
    public void getCurrentUser(String uid, Callback<User> callback) {
        firebaseFirestore.collection(FirebaseConstants.USERS).document(uid)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot.exists()) {
                            String fullName = documentSnapshot.get(FirebaseConstants.FULL_NAME, String.class);
                            String email = documentSnapshot.get(FirebaseConstants.EMAIL, String.class);
                            UserDTO userDTO = new UserDTO(uid, fullName, email);
                            callback.onSuccess(UserMapper.toUser(userDTO));
                        } else {
                            callback.onFailure("User doesn't exist.");
                        }
                    } else {
                        callback.onFailure("Task is not successful.");
                    }
                });
    }

    @Override
    public void confirmOrder(Order order, Callback<Void> callback) {
        if (getCurrentUser() != null) {
            OrderDTO orderDTO = OrderMapper.toOrderDTO(order);
            firebaseFirestore.collection(FirebaseConstants.USERS)
                    .document(getCurrentUser().getUid())
                    .collection(FirebaseConstants.ORDERS)
                    .document(orderDTO.getOrderId())
                    .set(orderDTO)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful())
                            callback.onSuccess(null);
                        else
                            callback.onFailure("Order couldn't be added! " + task.getException().getMessage());
                    });
        } else
            callback.onFailure("User not authenticated.");
    }

    @Override
    public void getPastOrders(Callback<List<Order>> callback) {
        firebaseFirestore.collection(FirebaseConstants.USERS)
                .document(getCurrentUser().getUid())
                .collection(FirebaseConstants.ORDERS)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Order> pastOrders = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            OrderDTO orderDTO = document.toObject(OrderDTO.class);
                            Order order = OrderMapper.toOrder(orderDTO);
                            pastOrders.add(order);
                        }
                        callback.onSuccess(pastOrders);
                    } else {
                        callback.onFailure("Past orders couldn't be fetched!");
                    }
                });
    }

    @Override
    public void getOrderById(String orderId, Callback<Order> callback) {
        firebaseFirestore.collection(FirebaseConstants.USERS)
                .document(getCurrentUser().getUid())
                .collection(FirebaseConstants.ORDERS)
                .document(orderId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        OrderDTO orderDTO = task.getResult().toObject(OrderDTO.class);
                        Order order = OrderMapper.toOrder(orderDTO);
                        callback.onSuccess(order);
                    } else {
                        callback.onFailure("Order couldn't be fetched!");
                    }
                });
    }

}
