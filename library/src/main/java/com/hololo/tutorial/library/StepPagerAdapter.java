package com.hololo.tutorial.library;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class StepPagerAdapter extends FragmentStatePagerAdapter {
    private List<Step> stepList;

    public StepPagerAdapter(FragmentManager fm, List<Step> stepList) {
        super(fm);
        this.stepList = stepList;
    }

    @Override
    public Fragment getItem(int position) {
        Step step = stepList.get(position);

        return StepFragment.createFragment(step);
    }

    @Override
    public int getCount() {
        return stepList.size();
    }
}
