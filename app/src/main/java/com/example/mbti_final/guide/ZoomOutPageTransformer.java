package com.example.mbti_final.guide;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
    private static final float MIN_SCALE = 0.93f;
    private static final float MIN_ALPHA = 0.6f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();

        if(position < -1){
            page.setAlpha(0f);
        } else if (position <= 1){
            // 슬라이드를 통한 기본 전환 효과를 수정
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                page.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                page.setTranslationX(-horzMargin + vertMargin / 2);
            }
            // MIN_SCALE과 1사이 크기만큼 화면 조정
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);

            //
            //크기에 비례하여 페이지를 페이드
            page.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        } else { // (1,+Infinity]
            // 이 페이지는 화면 오른쪽을 의미
            page.setAlpha(0f);
        }
    }
}
