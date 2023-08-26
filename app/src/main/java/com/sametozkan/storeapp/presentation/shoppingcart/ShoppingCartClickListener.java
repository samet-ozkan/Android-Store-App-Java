package com.sametozkan.storeapp.presentation.shoppingcart;

import com.sametozkan.storeapp.domain.model.Product;

public interface ShoppingCartClickListener {

    void onConfirmOrderClicked();
    void onItemClicked(Product product);
    void onRemoveItemClicked(Product product);
}
