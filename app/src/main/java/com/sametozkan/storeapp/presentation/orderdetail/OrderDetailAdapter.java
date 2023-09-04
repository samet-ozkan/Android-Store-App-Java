package com.sametozkan.storeapp.presentation.orderdetail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.ItemProductLinearBinding;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.util.ListAdapter;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>
implements ListAdapter {

    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    private ProductClickListener productClickListener;

    public OrderDetailAdapter(ProductClickListener productClickListener) {
        this.productClickListener = productClickListener;
    }

    @NonNull
    @Override
    public OrderDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductLinearBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_product_linear, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.ViewHolder holder, int position) {
        Product product = productList.get(holder.getAdapterPosition());
        holder.bindItem(product);
    }

    @Override
    public int getItemCount() {
        return (productList == null) ? 0 : productList.size();
    }

    @Override
    public void setList(List list) {
        setProductList(list);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemProductLinearBinding binding;

        public ViewHolder(ItemProductLinearBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(Product product) {
            binding.setProduct(product);
            binding.setItemClickListener(productClickListener);
        }
    }
}
