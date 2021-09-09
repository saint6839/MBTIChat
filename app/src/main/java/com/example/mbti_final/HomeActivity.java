package com.example.mbti_final;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mbti_final.ChatRoom.ChatListFragment;
import com.example.mbti_final.ChatRoom.ChatRoomFragment;
import com.example.mbti_final.ChatRoom.ChatRecyclerViewAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class HomeActivity extends FragmentActivity {
    private static final String TAG = "HomeActivity.class";     // 로그 TAG
    private long backpressedTime = 0;                           // onBackPressed 관련 선언
    private RecyclerView recyclerView_chat;                     // 채팅방 목록 관련 선언
    private ChatRecyclerViewAdapter chatRecyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference databaseReference;                // RealtimeDB 관련 선언
    private FirebaseUser firebaseUser;

    private TabLayout tab_home;
    ChatRoomFragment chatRoomFragment;
    ChatListFragment chatListFragment;

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
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("name", firebaseUser.getDisplayName());
        userData.put("email", firebaseUser.getEmail());
        userData.put("uid", firebaseUser.getUid());
        databaseReference.child(firebaseUser.getUid()).setValue(userData);
    }

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

