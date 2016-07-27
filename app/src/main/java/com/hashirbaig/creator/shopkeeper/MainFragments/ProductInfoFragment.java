package com.hashirbaig.creator.shopkeeper.MainFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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

import com.hashirbaig.creator.shopkeeper.HostingActivities.ProductInfoActivity;
import com.hashirbaig.creator.shopkeeper.Model.Product;
import com.hashirbaig.creator.shopkeeper.Model.ProductsData;
import com.hashirbaig.creator.shopkeeper.R;

import java.util.UUID;

public class ProductInfoFragment extends Fragment{

    private EditText mProductName;
    private EditText mSerialNumber;
    private EditText mProductStock;
    private EditText mProductPrice;

    private boolean isEditing;

    private Product mProduct;

    public static ProductInfoFragment newInstance() {
        return new ProductInfoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID uuid = (UUID) getActivity().getIntent().getSerializableExtra(ProductInfoActivity.KEY_UUID);
        if(uuid == null) {
            mProduct = new Product();
            isEditing = false;
        } else {
            mProduct = ProductsData.get(getActivity()).find(uuid);
            isEditing = true;
        }
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info_product, container, false);

        mProductName = (EditText) v.findViewById(R.id.product_name_fragment);
        mSerialNumber = (EditText) v.findViewById(R.id.product_serial);
        mProductStock = (EditText) v.findViewById(R.id.product_amount_stock);
        mProductPrice = (EditText) v.findViewById(R.id.product_price);

        if(mProduct.getName() != null) {
            mProductName.setText(mProduct.getName());
            mSerialNumber.setText(mProduct.getSerialNumber());
            mProductStock.setText(mProduct.getAmountInStock().toString());
            mProductPrice.setText(Double.toString(mProduct.getPrice()));
        }

        mProductName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProduct.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSerialNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    mProduct.setSerialNumber(s.toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Invalid Number!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mProductStock.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    mProduct.setAmountInStock(Integer.parseInt(s.toString()));
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Invalid Price.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mProductPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProduct.setPrice(Double.parseDouble(s.toString()));
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
        inflater.inflate(R.menu.fragment_info_product, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_option:
                if(mProduct.isOK()) {
                    if(!isEditing) {
                        ProductsData.get(getActivity()).add(mProduct);
                    } else {
                        ProductsData.get(getActivity()).update(mProduct);
                    }
                    getActivity().finish();
                } else {
                    Snackbar.make(getView(), "One or more necessary fields are empty.", Snackbar.LENGTH_LONG).show();
                }
                return true;
            case R.id.delete_option:
                if(isEditing) {
                    ProductsData.get(getActivity()).delete(mProduct);
                }
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
