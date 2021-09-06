package com.example.mbti_final.guide;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mbti_final.HomeActivity;
import com.example.mbti_final.MainActivity;
import com.example.mbti_final.R;
import com.facebook.login.LoginManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class GuideFragment extends Fragment {
    Button btn_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_guide,container,false);

        btn_logout = rootView.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        return rootView;
    }
}