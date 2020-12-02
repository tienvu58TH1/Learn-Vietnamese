package com.example.projectda.adapter;


import com.example.projectda.fragments.TopSTTFragment;
import com.example.projectda.fragments.TopTTSFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerTopPlayAdapter extends FragmentPagerAdapter {

    public ViewPagerTopPlayAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if (position==0){
            fragment=new TopTTSFragment();
        }else if (position==1){
            fragment=new TopSTTFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        if (position==0){
            title= "Top Listening";
        }else if (position==1){
            title= "Top Speaking";
        }
        return title;
    }
}
