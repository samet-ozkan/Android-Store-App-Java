package com.sametozkan.storeapp.presentation.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sametozkan.storeapp.databinding.ItemProductBinding;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.domain.model.Product;
import com.sametozkan.storeapp.presentation.product.ProductDetailActivity;
import com.sametozkan.storeapp.util.IntentConstants;
import com.sametozkan.storeapp.util.ListAdapter;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
implements ListAdapter {

    private static final String TAG = "ProductAdapter";

    private List<Product> productList;
    private Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void setProductList(List<Product> productList) {
        Log.d(TAG, "setProductList: " + productList);
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ItemProductBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_product, parent, false);
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

    @Override
    public void setList(List list) {
        setProductList(list);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements ProductClickListener {

        private ItemProductBinding binding;

        public ViewHolder(@NonNull ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(Product product) {
            binding.setProduct(product);
            binding.setClickListener(this);
        }

        @Override
        public void onProductClicked(Product product) {
            final Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra(IntentConstants.PRODUCT_ID, product.getId());
            context.startActivity(intent);
        }
    }
}
