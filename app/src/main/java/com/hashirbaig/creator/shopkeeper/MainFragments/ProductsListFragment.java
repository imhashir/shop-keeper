package com.hashirbaig.creator.shopkeeper.MainFragments;

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

import com.hashirbaig.creator.shopkeeper.HostingActivities.ProductInfoActivity;
import com.hashirbaig.creator.shopkeeper.Model.Product;
import com.hashirbaig.creator.shopkeeper.Model.ProductsData;
import com.hashirbaig.creator.shopkeeper.R;

import java.util.List;

public class ProductsListFragment extends Fragment{

    private List<Product> mProductList;
    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private FloatingActionButton mNewProduct;

    public static ProductsListFragment newInstance() {
        return new ProductsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductList = ProductsData.get(getActivity()).getProductList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.list_container);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewProduct = (FloatingActionButton) v.findViewById(R.id.new_receipt);

        mNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = ProductInfoActivity.newIntent(getActivity(), null);
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
        mAdapter = new ProductAdapter(mProductList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mName;
        private TextView mProductStock;
        private TextView mPrice;
        private Product mProduct;

        public ProductHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            mName = (TextView) v.findViewById(R.id.product_name_single);
            mProductStock = (TextView) v.findViewById(R.id.amount_in_stock);
            mPrice = (TextView) v.findViewById(R.id.product_price_text);
        }

        public void bindHolder(Product p) {
            mProduct = p;
            mName.setText(mProduct.getName());
            mProductStock.setText(p.getAmountInStock().toString());
            mPrice.setText(Double.toString(p.getPrice()));
        }

        @Override
        public void onClick(View v) {
            Intent i = ProductInfoActivity.newIntent(getActivity(), mProduct.getUUID());
            startActivity(i);
        }
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {

        private List<Product> mList;

        public ProductAdapter(List<Product> list) {
            mList = list;
        }

        @Override
        public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.single_product_layout, parent, false);
            return new ProductHolder(v);
        }

        @Override
        public void onBindViewHolder(ProductHolder holder, int position) {
            holder.bindHolder(ProductsData.get(getActivity()).get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
