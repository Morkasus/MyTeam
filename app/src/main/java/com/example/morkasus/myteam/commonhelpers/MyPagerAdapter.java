package com.example.morkasus.myteam.commonhelpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.morkasus.myteam.manager.MembersFragment;
import com.example.morkasus.myteam.manager.TasksFragment;


/**
 * Created by morkasus on 18/12/2015.
 */
public class MyPagerAdapter extends SmartFragmentStatePagerAdapter {

    private static final int NUM_ITEMS = 2;
    public static final int POSITION_MEMBERS = 0;
    public static final int POSITION_TASKS = 1;

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MembersFragment.newInstance(POSITION_MEMBERS, "Page # 1");
            case 1:
                return TasksFragment.newInstance(POSITION_TASKS, "Page # 2");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case POSITION_MEMBERS:
                return "My Team Members";
            case POSITION_TASKS:
                return "Team Tasks";
        }
        return "";
    }

}
