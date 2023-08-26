package com.sametozkan.storeapp.data.datasource.local.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {

    private static final String TAG = "ShoppingCart";
    private static final String PREF_NAME = "ShoppingCartPrefs";
    private final String CART_KEY;

    private SharedPreferences sharedPreferences;

    public ShoppingCart(Context context, String userEmail) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        CART_KEY = userEmail;
    }

    public void addProductIdToCart(int productId) {
        Set<String> cartItems = getCartItems();
        cartItems.add(String.valueOf(productId));
        saveCartItem(cartItems);
    }

    public void removeProductIdFromCart(int productId) {
        Set<String> cartItems = getCartItems();
        if (cartItems.remove(String.valueOf(productId))
        ) {
            Log.d(TAG, "removeProductIdFromCart: cartItem removed successfully!");
            saveCartItem(cartItems);
        } else {
            Log.e(TAG, "removeProductIdFromCart: cartItem couldn't be removed!");
        }
    }

    public Set<String> getCartItems() {
        return sharedPreferences.getStringSet(CART_KEY, new HashSet<>());
    }

    public void clearCart(){
        saveCartItem(new HashSet<>());
    }

    private void saveCartItem(Set<String> cartItems) {
        sharedPreferences.edit().putStringSet(CART_KEY, cartItems).apply();
    }

}
