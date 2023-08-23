package com.sametozkan.storeapp.data.datasource.local.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {

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

    public Set<String> getCartItems() {
        return sharedPreferences.getStringSet(CART_KEY, new HashSet<>());
    }

    private void saveCartItem(Set<String> cartItems) {
        sharedPreferences.edit().putStringSet(CART_KEY, cartItems).apply();
    }
}
