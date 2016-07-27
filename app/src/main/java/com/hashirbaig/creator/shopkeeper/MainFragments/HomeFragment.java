package com.hashirbaig.creator.shopkeeper.MainFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hashirbaig.creator.shopkeeper.HostingActivities.CustomerInfoActivity;
import com.hashirbaig.creator.shopkeeper.HostingActivities.ProductInfoActivity;
import com.hashirbaig.creator.shopkeeper.R;

public class HomeFragment extends Fragment{

    private ImageView mAddReceiptButton;
    private ImageView mAddProductButton;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mAddReceiptButton = (ImageView) v.findViewById(R.id.add_receipt_home);
        mAddProductButton = (ImageView) v.findViewById(R.id.add_product_home);

        mAddReceiptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CustomerInfoActivity.newIntent(getActivity(), null);
                startActivity(i);
            }
        });

        mAddProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = ProductInfoActivity.newIntent(getActivity(), null);
                startActivity(i);
            }
        });

        return v;
    }
}
