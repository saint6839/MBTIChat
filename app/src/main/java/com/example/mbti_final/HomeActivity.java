package com.example.mbti_final;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mbti_final.ChatRoom.ChatListFragment;
import com.example.mbti_final.ChatRoom.ChatRoomFragment;
import com.example.mbti_final.ChatRoom.ChatRecyclerViewAdapter;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class HomeActivity extends FragmentActivity {
    private static final String TAG = "HomeActivity.class";     // 로그 TAG
    private long backpressedTime = 0;                           // onBackPressed 관련 선언
    private DatabaseReference databaseReference;                // RealtimeDB 관련 선언
    private FirebaseUser firebaseUser;
    private TabLayout tab_home;                                 // 상단 TabLayout 관련 선언
    ChatRoomFragment chatRoomFragment;
    ChatListFragment chatListFragment;
    ValueEventListener postListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        inputUserData();    // 사용자 로그인 정보 DB 저장
        onTabChaned();      // 상단 탭 변경 시 이벤트
    }

    // 테스트 "Users" DB작성
    private void inputUserData(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("name", firebaseUser.getDisplayName());
        userData.put("email", firebaseUser.getEmail());
        userData.put("uid", firebaseUser.getUid());
        userData.put("nickname", "닉네임 미지정");

        databaseReference.child("nickname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String nickname = (String) task.getResult().getValue();
                if(!task.isSuccessful()){
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    if(nickname==null){
                        DialogFragment dialogFragment = new NicknameFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("uid",firebaseUser.getUid());
                        dialogFragment.setArguments(bundle);
                        dialogFragment.show(getSupportFragmentManager(), "nickname");
                    }
                }
            }
        });

        databaseReference.setValue(userData);
    }

    // 상단 탭 변경 시 탭별 프래그먼트 전환
    private void onTabChaned(){
        chatRoomFragment = new ChatRoomFragment();
        chatListFragment = new ChatListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, chatRoomFragment).commit();
        tab_home = findViewById(R.id.tab_home);

        tab_home.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if(position == 0 )
                    selected = chatRoomFragment;
                else if(position == 1)
                    selected = chatListFragment;
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // 두 번 빠르게 터치할 시 어플 종료 ( + 로그인 화면으로 돌아가는 것 방지 )
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

