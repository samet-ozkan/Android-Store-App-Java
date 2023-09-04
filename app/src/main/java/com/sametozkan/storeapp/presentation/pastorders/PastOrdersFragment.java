package com.sametozkan.storeapp.presentation.pastorders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sametozkan.storeapp.MyApplication;
import com.sametozkan.storeapp.R;
import com.sametozkan.storeapp.databinding.FragmentPastOrdersBinding;
import com.sametozkan.storeapp.domain.model.Order;
import com.sametozkan.storeapp.presentation.ViewModelFactory;
import com.sametozkan.storeapp.presentation.orderdetail.OrderDetailActivity;
import com.sametozkan.storeapp.util.IntentConstants;
import com.sametozkan.storeapp.util.Utils;

import javax.inject.Inject;

public class PastOrdersFragment extends Fragment implements OrderClickListener {

    @Inject
    ViewModelFactory viewModelFactory;

    private PastOrdersViewModel viewModel;

    private FragmentPastOrdersBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MyApplication) getContext().getApplicationContext()).getAppComponent().inject(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_past_orders, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViewModel();
        binding.setViewModel(viewModel);
        binding.setAdapter(new PastOrdersAdapter(this));
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(PastOrdersViewModel.class);
    }

    @Override
    public void onOrderClicked(Order order) {
        final Intent intent = new Intent(getContext(), OrderDetailActivity.class);
        intent.putExtra(IntentConstants.ORDER_ID, order.getOrderId());
        startActivity(intent);
    }
}
