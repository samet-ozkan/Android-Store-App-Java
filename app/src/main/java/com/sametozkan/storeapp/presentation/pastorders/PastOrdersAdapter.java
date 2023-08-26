package com.sametozkan.storeapp.presentation.pastorders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.ItemOrderBinding;
import com.sametozkan.storeapp.domain.model.Order;

import java.util.List;

public class PastOrdersAdapter extends RecyclerView.Adapter<PastOrdersAdapter.ViewHolder> {

    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    private OrderClickListener orderClickListener;

    public PastOrdersAdapter(OrderClickListener orderClickListener){
        this.orderClickListener = orderClickListener;
    }

    @NonNull
    @Override
    public PastOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_order, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PastOrdersAdapter.ViewHolder holder, int position) {
        Order order = orderList.get(holder.getAdapterPosition());
        holder.bindItem(order);
    }

    @Override
    public int getItemCount() {
        return (orderList == null) ? 0 : orderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemOrderBinding binding;

        public ViewHolder(@NonNull ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(Order order) {

            binding.setOrder(order);
            binding.setClickListener(orderClickListener);
        }
    }
}
