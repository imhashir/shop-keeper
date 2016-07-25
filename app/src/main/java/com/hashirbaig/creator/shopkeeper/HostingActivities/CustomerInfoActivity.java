package com.hashirbaig.creator.shopkeeper.HostingActivities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hashirbaig.creator.shopkeeper.MainFragments.CustomerInfoFragment;
import com.hashirbaig.creator.shopkeeper.R;
import com.hashirbaig.creator.shopkeeper.SingleFragmentActivity;

public class CustomerInfoActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return CustomerInfoFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, CustomerInfoActivity.class);
        return i;
    }

}
