package com.example.mbti_final;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class CustomLoadingDiaglog extends Dialog {
    ImageView loading_img;

    public CustomLoadingDiaglog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_loading_diaglog);
        loading_img = findViewById(R.id.loading_img);
        setCancelable(false);

        Animation anim = AnimationUtils.loadAnimation(getContext(),R.anim.anim_rotate);
        loading_img.startAnimation(anim);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}