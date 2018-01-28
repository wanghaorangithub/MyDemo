package com.example.a24078.myapplication;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    final List<Fragment> fgLists = new ArrayList<>(4);
    ChatFragment chatFragment;
    FriendsFragment friendsFragment;
    HomeFragment homeFragment;
    MoreFragment moreFragment;
    ViewPagerAdapter adapter;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_chat:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_friends:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_home:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.item_more:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                }
        );
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3); //预加载剩下两页
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        chatFragment = new ChatFragment();
        friendsFragment = new FriendsFragment();
        homeFragment = new HomeFragment();
        moreFragment = new MoreFragment();
        fgLists.add(chatFragment);
        fgLists.add(friendsFragment);
        fgLists.add(homeFragment);
        fgLists.add(moreFragment);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fgLists);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }
}
