package com.hashirbaig.creator.shopkeeper.MainFragments;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hashirbaig.creator.shopkeeper.HostingActivities.CustomerInfoActivity;
import com.hashirbaig.creator.shopkeeper.Model.Customer;
import com.hashirbaig.creator.shopkeeper.Model.CustomersData;
import com.hashirbaig.creator.shopkeeper.R;

import java.util.List;

public class CustomersListFragment extends Fragment{

    private List<Customer> mCustomerList;
    private RecyclerView mRecyclerView;
    private CustomerAdapter mAdapter;
    private FloatingActionButton mNewReceipt;

    public static CustomersListFragment newInstance() {
        return new CustomersListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomerList = CustomersData.get(getActivity()).getCustomerList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.customers_list_fragment, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.list_container);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewReceipt = (FloatingActionButton) v.findViewById(R.id.new_receipt);

        mNewReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CustomerInfoActivity.newIntent(getActivity(), null);
                startActivity(i);
            }
        });

        updateUI();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI() {
        mAdapter = new CustomerAdapter(mCustomerList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class CustomerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mName;
        private Customer mCustomer;

        public CustomerHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            mName = (TextView) v.findViewById(R.id.customer_name);
        }

        public void bindHolder(Customer c) {
            mCustomer = c;
            mName.setText(mCustomer.getName());
        }

        @Override
        public void onClick(View v) {
            Intent i = CustomerInfoActivity.newIntent(getActivity(), mCustomer.getUUID());
            startActivity(i);
        }
    }

    private class CustomerAdapter extends RecyclerView.Adapter<CustomerHolder> {

        private List<Customer> mList;

        public CustomerAdapter(List<Customer> list) {
            mList = list;
        }

        @Override
        public CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.single_customer_layout, parent, false);
            return new CustomerHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomerHolder holder, int position) {
            holder.bindHolder(CustomersData.get(getActivity()).get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
