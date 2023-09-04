package com.sametozkan.storeapp.presentation.categoryList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.ItemCategoryListBinding;
import com.sametozkan.storeapp.presentation.category.CategoryActivity;
import com.sametozkan.storeapp.presentation.category.CategoryClickListener;
import com.sametozkan.storeapp.util.BindingAdapter;
import com.sametozkan.storeapp.util.IntentConstants;
import com.sametozkan.storeapp.util.ListAdapter;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder>
implements ListAdapter {

    private Context context;

    private List<String> categoryList;

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public CategoryListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_category_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, int position) {
        holder.bindItem(categoryList.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return (categoryList == null) ? 0 : categoryList.size();
    }

    @Override
    public void setList(List list) {
        setCategoryList(list);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements CategoryClickListener {

        private ItemCategoryListBinding binding;

        public ViewHolder(@NonNull ItemCategoryListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(String categoryName) {
            binding.setCategoryName(categoryName);
            binding.setCategoryClickListener(this);
        }

        @Override
        public void onCategoryClicked(String categoryName) {
            final Intent intent = new Intent(context, CategoryActivity.class);
            intent.putExtra(IntentConstants.CATEGORY, categoryName);
            context.startActivity(intent);
        }
    }
}
