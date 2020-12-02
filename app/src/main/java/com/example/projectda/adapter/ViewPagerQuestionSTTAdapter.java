package com.example.projectda.adapter;


import android.util.Log;
import android.view.ViewGroup;

import com.example.projectda.fragments.QuestionSTTFragment;
import com.example.projectda.models.Question;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerQuestionSTTAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Question> mQuestions;

    public ViewPagerQuestionSTTAdapter(@NonNull FragmentManager fm, ArrayList<Question> questions) {
        super(fm);
        mQuestions=questions;
    }

    public void updateAdapter(ArrayList<Question> questions){
        mQuestions=questions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        Log.d("Long"," instantiateItem Position " + position);
        //getItem(position);
        return super.instantiateItem(container, position);

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d("Long"," destroyItem Position " + position);

        super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment= new QuestionSTTFragment(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mQuestions.size();
    }

}
