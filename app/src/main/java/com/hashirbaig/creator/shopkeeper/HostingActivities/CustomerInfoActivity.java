package com.hashirbaig.creator.shopkeeper.HostingActivities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.hashirbaig.creator.shopkeeper.MainFragments.CustomerInfoFragment;
import com.hashirbaig.creator.shopkeeper.R;
import com.hashirbaig.creator.shopkeeper.SingleFragmentActivity;

import java.util.UUID;

public class CustomerInfoActivity extends SingleFragmentActivity {

    public static final String KEY_UUID = "com.hashirbaig.creator.shopkeeper.HostingActivities.CustomerInfoActivity.uuid";

    @Override
    public Fragment createFragment() {
        return CustomerInfoFragment.newInstance();
    }

    public static Intent newIntent(Context context, UUID uuid) {
        Intent i = new Intent(context, CustomerInfoActivity.class);
        i.putExtra(KEY_UUID, uuid);
        return i;
    }
}
