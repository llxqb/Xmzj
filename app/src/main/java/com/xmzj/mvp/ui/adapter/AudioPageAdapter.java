package com.xmzj.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xmzj.mvp.ui.fragment.AudioFragment;

import java.util.ArrayList;
import java.util.List;

public class AudioPageAdapter extends FragmentPagerAdapter {
    private final String[] titles ;
    private List<Fragment> frag;

    public AudioPageAdapter(FragmentManager fm,List<Fragment> fragmentList, String[] titleString) {
        super(fm);
        frag= fragmentList;
        titles = titleString;
    }


    @Override
    public Fragment getItem(int position) {
        return frag.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
