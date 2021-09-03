package com.example.mbti_final.guide;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mbti_final.guide.GuideFragment;
import com.example.mbti_final.guide.GuideFragment2;
import com.example.mbti_final.guide.GuideFragment3;
import com.example.mbti_final.guide.GuideFragment4;
import com.example.mbti_final.guide.GuideFragment5;

public class GuidePagerAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 5;

    public GuidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0)
            return new GuideFragment();
        else if(position == 1)
            return new GuideFragment2();
        else if(position == 2)
            return  new GuideFragment3();
        else if(position == 3)
            return  new GuideFragment4();
        else
            return new GuideFragment5();
    }
    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
