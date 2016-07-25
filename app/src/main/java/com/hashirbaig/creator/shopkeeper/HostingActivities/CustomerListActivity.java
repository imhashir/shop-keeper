package com.hashirbaig.creator.shopkeeper.HostingActivities;

import android.support.v4.app.Fragment;

import com.hashirbaig.creator.shopkeeper.MainFragments.CustomersListFragment;
import com.hashirbaig.creator.shopkeeper.SingleFragmentActivity;

public class CustomerListActivity extends SingleFragmentActivity{

    @Override
    public Fragment createFragment() {
        return CustomersListFragment.newInstance();
    }
}
