package com.example.mbti_final;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mbti_final.guide.GuidePagerAdapter;
import com.example.mbti_final.guide.ZoomOutPageTransformer;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends FragmentActivity {
    private static final String TAG = "HomeActivity.class";
    private long backpressedTime = 0;

    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    private TabLayout tab_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.viewPager2_Home);
        pagerAdapter = new GuidePagerAdapter(this);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        viewPager.setAdapter(pagerAdapter);

        tab_home = findViewById(R.id.tab_home);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backpressedTime + 2000) {
            backpressedTime = System.currentTimeMillis();
            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() <= backpressedTime + 2000) {
            moveTaskToBack(true); // 태스크를 백그라운드로 이동
            finishAndRemoveTask(); // 액티비티 종료 + 태스크 리스트에서 지우기
            System.exit(0);
        }
    }

}

