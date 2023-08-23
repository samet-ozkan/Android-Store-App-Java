package com.sametozkan.storeapp.util;

public interface Callback<T> {
    void onSuccess(T result);
    void onFailure(String errorMessage);
}
