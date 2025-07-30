package com.example.vts.Activity.EravanaIdPAck;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.vts.Activity.Fragment.AllManualTripList;
import com.example.vts.Activity.Fragment.AllTripList;
import com.example.vts.Activity.Fragment.CompleteManualTrip;
import com.example.vts.Activity.Fragment.CompleteTrip;
import com.example.vts.Activity.Fragment.RunningManualTrip;
import com.example.vts.Activity.Fragment.RunningTrip;

public  class MyAdapter1 extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public MyAdapter1(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllManualTripList footballFragment = new AllManualTripList();
                return footballFragment;
            case 1:
                RunningManualTrip cricketFragment = new RunningManualTrip();
                return cricketFragment;
            case 2:
                CompleteManualTrip cricketFragment1 = new CompleteManualTrip();
                return cricketFragment1;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}