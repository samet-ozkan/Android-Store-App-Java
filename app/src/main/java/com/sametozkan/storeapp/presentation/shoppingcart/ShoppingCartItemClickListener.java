package com.sametozkan.storeapp.presentation.shoppingcart;

import com.sametozkan.storeapp.domain.model.Product;

public interface ShoppingCartItemClickListener {

    void onRemoveItemClicked(Product product);
}
