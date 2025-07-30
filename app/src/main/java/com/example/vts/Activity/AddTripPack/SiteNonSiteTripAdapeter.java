package com.example.vts.Activity.AddTripPack;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.vts.Activity.Fragment.AllManualTripList;
import com.example.vts.Activity.Fragment.CompleteManualTrip;
import com.example.vts.Activity.Fragment.RunningManualTrip;
import com.example.vts.Activity.Fragment.SiteWiseTripFragment;
import com.example.vts.Activity.Fragment.WithoutSiteFragment;

public class SiteNonSiteTripAdapeter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;

    public SiteNonSiteTripAdapeter(Context context,FragmentManager fm, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SiteWiseTripFragment footballFragment = new SiteWiseTripFragment();
                return footballFragment;
            case 1:
                WithoutSiteFragment cricketFragment = new WithoutSiteFragment();
                return cricketFragment;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
