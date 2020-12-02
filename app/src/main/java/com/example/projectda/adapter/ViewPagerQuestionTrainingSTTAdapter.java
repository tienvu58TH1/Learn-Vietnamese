package com.example.projectda.adapter;


import com.example.projectda.activity.ViewPagerQuestionTrainingSTTActivity;
import com.example.projectda.fragments.QuestionTrainingSTTFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerQuestionTrainingSTTAdapter extends FragmentStatePagerAdapter {
    public ViewPagerQuestionTrainingSTTAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment= new QuestionTrainingSTTFragment(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return ViewPagerQuestionTrainingSTTActivity.mQuestionTopicViewPagerSTT.size();
    }

}
