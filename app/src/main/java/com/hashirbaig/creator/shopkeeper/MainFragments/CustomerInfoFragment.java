package com.hashirbaig.creator.shopkeeper.MainFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.hashirbaig.creator.shopkeeper.HostingActivities.CustomerInfoActivity;
import com.hashirbaig.creator.shopkeeper.Model.Customer;
import com.hashirbaig.creator.shopkeeper.Model.CustomersData;
import com.hashirbaig.creator.shopkeeper.R;

import java.util.UUID;

public class CustomerInfoFragment extends Fragment{

    private EditText mCustomerName;
    private EditText mNumber;
    private EditText mProduct;
    private EditText mPrice;

    private Customer mCustomer;

    public static CustomerInfoFragment newInstance() {
        return new CustomerInfoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID uuid = (UUID) getActivity().getIntent().getSerializableExtra(CustomerInfoActivity.KEY_UUID);
        if(uuid == null) {
            mCustomer = new Customer();
        } else {
            mCustomer = CustomersData.get(getActivity()).find(uuid);
        }
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info_customer, container, false);

        mCustomerName = (EditText) v.findViewById(R.id.customer_name);
        mNumber = (EditText) v.findViewById(R.id.mobile_number);
        mProduct = (EditText) v.findViewById(R.id.product_name);
        mPrice = (EditText) v.findViewById(R.id.sale_price);

        if(mCustomer.getName() != null) {
            mCustomerName.setText(mCustomer.getName());
            mNumber.setText(mCustomer.getNumber());
            mProduct.setText(mCustomer.getProduct());
            mPrice.setText(Double.toString(mCustomer.getPurchasePrice()));
        }

        mCustomerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCustomer.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    mCustomer.setNumber(s.toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Invalid Number!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    mCustomer.setPurchasePrice(Double.parseDouble(s.toString()));
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Invalid Price.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCustomer.setProduct(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_info_customer, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_option:
                if(mCustomer.isOK()) {
                    CustomersData.get(getActivity()).add(mCustomer);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "One or more necessary fields are empty.", Toast.LENGTH_LONG).show();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
