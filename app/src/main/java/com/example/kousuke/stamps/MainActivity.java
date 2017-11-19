package com.example.kousuke.stamps;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    static MainActivity instance = null;

    private GoogleApiConnector googleApiConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(this);

        tabLayout.setupWithViewPager(viewPager);

        googleApiConnector = new GoogleApiConnector();
        googleApiConnector.build(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiConnector.connect();
    }

    @Override
    protected void onStop() {
        googleApiConnector.disconnect();
        super.onStop();
    }

    // どこからでもMainActivityを参照できるようにするためのメソッド
    static MainActivity getInstance() {
        return instance;
    }

    // *********************************************************************************************

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("MainActivity", "onPageSelected() position=" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    // *********************************************************************************************
}