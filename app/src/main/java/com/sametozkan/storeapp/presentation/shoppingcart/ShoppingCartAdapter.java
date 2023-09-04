package com.sametozkan.storeapp.presentation.shoppingcart;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.ItemCartProductBinding;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.util.ListAdapter;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>
implements ListAdapter {

    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    private ShoppingCartClickListener shoppingCartClickListener;
    public ShoppingCartAdapter(ShoppingCartClickListener shoppingCartClickListener){
        this.shoppingCartClickListener = shoppingCartClickListener;
    }

    @NonNull
    @Override
    public ShoppingCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartProductBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_cart_product, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartAdapter.ViewHolder holder, int position) {
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

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ItemCartProductBinding binding;

        public ViewHolder(@NonNull ItemCartProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(Product product) {
            binding.setProduct(product);
            binding.setItemClickListener(shoppingCartClickListener);
        }
    }
}
