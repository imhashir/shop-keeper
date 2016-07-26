package com.hashirbaig.creator.shopkeeper.HostingActivities;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hashirbaig.creator.shopkeeper.MainFragments.CustomersListFragment;
import com.hashirbaig.creator.shopkeeper.MainFragments.HomeFragment;
import com.hashirbaig.creator.shopkeeper.R;

public class HomeActivity extends AppCompatActivity{

    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout)findViewById(R.id.nav_drawer_view);

        setSupportActionBar(mToolbar);
        setupDrawerContent(mNavigationView);

        mDrawerToggle = setupDrawerToggle();
        mDrawerToggle.syncState();

        Fragment fragment = HomeFragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container_drawer, fragment).commit();
    }

    private ActionBarDrawerToggle setupDrawerToggle(){
        return new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
    }

    private void setupDrawerContent(NavigationView mNavView) {
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                onItemSelected(item);
                return true;
            }
        });
    }

    private void onItemSelected(MenuItem item) {

        Fragment fragment = null;
        Class fragmentClass;

        switch (item.getItemId()) {
            case R.id.customer_list_nav:
                fragmentClass = CustomersListFragment.class;
                break;
            case R.id.home_nav:
                fragmentClass = HomeFragment.class;
                break;
            default:
                fragmentClass = CustomersListFragment.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container_drawer, fragment)
                .commit();

        item.setChecked(true);
        mDrawer.closeDrawers();
        setTitle(item.getTitle());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
