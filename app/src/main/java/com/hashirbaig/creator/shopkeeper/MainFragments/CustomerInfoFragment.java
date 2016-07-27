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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hashirbaig.creator.shopkeeper.HostingActivities.CustomerInfoActivity;
import com.hashirbaig.creator.shopkeeper.Model.Customer;
import com.hashirbaig.creator.shopkeeper.Model.CustomersData;
import com.hashirbaig.creator.shopkeeper.Model.Product;
import com.hashirbaig.creator.shopkeeper.Model.ProductsData;
import com.hashirbaig.creator.shopkeeper.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerInfoFragment extends Fragment{

    private EditText mCustomerName;
    private EditText mNumber;
    private EditText mPrice;
    private Spinner mProductOptions;

    private boolean isEditing;

    private List<String> mProductNames;
    private List<Product> mProducts;
    private ArrayAdapter<String> mAdapter;

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
            isEditing = false;
        } else {
            mCustomer = CustomersData.get(getActivity()).find(uuid);
            isEditing = true;
        }
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info_customer, container, false);

        mCustomerName = (EditText) v.findViewById(R.id.customer_name);
        mNumber = (EditText) v.findViewById(R.id.mobile_number);
        mProductOptions = (Spinner) v.findViewById(R.id.product_name);
        mPrice = (EditText) v.findViewById(R.id.sale_price);

        populateDropdownView(mProductOptions);

        if(mCustomer.getName() != null) {
            mCustomerName.setText(mCustomer.getName());
            mNumber.setText(mCustomer.getNumber());
            mPrice.setText(Double.toString(mCustomer.getPurchasePrice()));

            int index = mAdapter.getPosition(mCustomer.getProduct());
            mProductOptions.setSelection(index);
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

        mProductOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = mProductOptions.getSelectedItem().toString();
                if(name != getString(R.string.select_spinner_item_text)) {
                    List<Product> list = ProductsData.get(getActivity()).getProductList();
                    for (Product product : list) {
                        if (product.getName().equals(name)) {
                            mCustomer.setProduct(product.getName());
                            mPrice.setHint(product.getPrice().toString());
                            if(product.getAmountInStock() > 0) {
                                product.setAmountInStock(product.getAmountInStock() - 1);
                                ProductsData.get(getActivity()).update(product);
                            }
                            return;
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

    public void populateDropdownView(Spinner spinner) {
        mProductNames = new ArrayList<>();
        mProducts = ProductsData.get(getActivity()).getProductList();
        mProductNames.add(getString(R.string.select_spinner_item_text));
        for (Product product : mProducts) {
            mProductNames.add(product.getName());
        }
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mProductNames);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mAdapter);
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
                if(mCustomer.getPurchasePrice() == null) {
                    mCustomer.setPurchasePrice(Double.parseDouble(mPrice.getHint().toString()));
                }
                if(mCustomer.isOK()) {
                    if(!isEditing) {
                        CustomersData.get(getActivity()).add(mCustomer);
                    } else {
                        CustomersData.get(getActivity()).update(mCustomer);
                    }
                    getActivity().finish();
                } else {
                    Snackbar.make(getView(), "One or more necessary fields are empty.", Snackbar.LENGTH_LONG).show();
                }
                return true;
            case R.id.delete_option:
                if(isEditing) {
                    CustomersData.get(getActivity()).delete(mCustomer);
                }
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
