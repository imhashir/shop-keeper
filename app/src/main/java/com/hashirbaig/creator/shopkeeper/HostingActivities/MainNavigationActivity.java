package com.hashirbaig.creator.shopkeeper.HostingActivities;

import android.support.v4.app.Fragment;

import com.hashirbaig.creator.shopkeeper.MainFragments.MainNavigationFragment;
import com.hashirbaig.creator.shopkeeper.SingleFragmentActivity;

public class MainNavigationActivity extends SingleFragmentActivity{

    @Override
    public Fragment createFragment() {
        return MainNavigationFragment.newInstance();
    }
}
