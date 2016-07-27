package com.hashirbaig.creator.shopkeeper.HostingActivities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.hashirbaig.creator.shopkeeper.MainFragments.ProductInfoFragment;
import com.hashirbaig.creator.shopkeeper.SingleFragmentActivity;

import java.util.UUID;

public class ProductInfoActivity extends SingleFragmentActivity{

    public static final String KEY_UUID = "ProductKeyUUID";

    @Override
    public Fragment createFragment() {
        return ProductInfoFragment.newInstance();
    }

    public static Intent newIntent(Context context, UUID uuid) {
        Intent i = new Intent(context, ProductInfoActivity.class);
        i.putExtra(KEY_UUID, uuid);
        return i;
    }
}
