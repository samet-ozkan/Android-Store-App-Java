package com.sametozkan.storeapp.presentation.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.ProductItemBinding;
import com.sametozkan.storeapp.domain.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ProductItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.product_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(holder.getAdapterPosition());
        holder.bindItem(product);
    }

    @Override
    public int getItemCount() {
        return (productList == null) ? 0 : productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ProductItemBinding binding;

        public ViewHolder(@NonNull ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(Product product) {
            binding.setProduct(product);
        }
    }
}
