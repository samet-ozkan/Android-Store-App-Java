package com.sametozkan.storeapp.presentation.pastorders;

import com.sametozkan.storeapp.domain.model.Order;

public interface OrderClickListener {

    void onOrderClicked(Order order);
}
