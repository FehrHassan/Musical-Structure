package com.fehr.nanodegree.musicalstructure.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.fehr.nanodegree.musicalstructure.R;
import com.fehr.nanodegree.musicalstructure.adapter.TabPagerAdapter;

public class MainActivity extends FragmentActivity {
    ViewPager viewPager;
    TabPagerAdapter tabPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar_main);
        toolbar.setLogo (R.mipmap.ic_launcher);
        toolbar.setTitle (R.string.app_name);
        toolbar.setTitleTextColor (getResources ().getColor (android.R.color.white, null));

        tabPagerAdapter = new TabPagerAdapter (getSupportFragmentManager (), MainActivity.this);

        viewPager = (ViewPager) findViewById (R.id.viewpager);
        viewPager.setAdapter (tabPagerAdapter);
        int i = getIntent ().getIntExtra ("fragment", 0);
        viewPager.setCurrentItem (i);

        TabLayout tabLayout = (TabLayout) findViewById (R.id.sliding_tabs);
        tabLayout.setupWithViewPager (viewPager);
    }
}