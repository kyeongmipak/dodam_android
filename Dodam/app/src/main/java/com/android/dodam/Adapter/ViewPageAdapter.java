package com.android.dodam.Adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

// 지은 추가 21.03.12 ***************************
// 메인에서 쓰이는 ViewPageAdapter
public class ViewPageAdapter extends FragmentPagerAdapter {


    private final List<Fragment> lstFrmt = new ArrayList<>();
    private final List<String> lstTitles = new ArrayList<>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return lstFrmt.get(position);
    }

    @Override
    public int getCount() {
        return lstTitles.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {

        return lstTitles.get(position);
    }

    public void AddFrmt(Fragment fragment, String titles){

        lstFrmt.add(fragment);
        lstTitles.add(titles);
    }
}
