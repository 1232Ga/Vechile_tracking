package com.example.vts.Activity.EravanaIdPAck;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.vts.Activity.Fragment.AllTripList;
import com.example.vts.Activity.Fragment.CompleteTrip;
import com.example.vts.Activity.Fragment.RunningTrip;

public  class MyAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    String Siteid;
    public MyAdapter(Context c, FragmentManager fm, int totalTabs,String siteid) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
        this.Siteid = siteid;
        System.out.println("fghgfhghgjj"+Siteid);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllTripList footballFragment = new AllTripList();
                return footballFragment;
            case 1:
                System.out.println("gjhghggh"+Siteid);
                RunningTrip cricketFragment = new RunningTrip();
                return cricketFragment;
            case 2:
                CompleteTrip cricketFragment1 = new CompleteTrip();
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