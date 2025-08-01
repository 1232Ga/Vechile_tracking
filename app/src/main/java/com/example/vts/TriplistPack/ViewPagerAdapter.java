package com.example.vts.TriplistPack;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private final List<String> mFragmentSiteid = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    public void addFragment(Fragment fragment, String title,String id) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        mFragmentSiteid.add(id);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentSiteid.get(position);
    }

}
