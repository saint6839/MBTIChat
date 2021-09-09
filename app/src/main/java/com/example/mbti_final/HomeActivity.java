package com.example.mbti_final;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mbti_final.ChatRoom.ChatData;
import com.example.mbti_final.ChatRoom.ChatItem;
import com.example.mbti_final.ChatRoom.ChatRecyclerViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends FragmentActivity {
    private static final String TAG = "HomeActivity.class";     // 로그 TAG
    private long backpressedTime = 0;                           // onBackPressed 관련 선언
    private RecyclerView recyclerView_chat;                     // 채팅방 목록 관련 선언
    private ChatRecyclerViewAdapter chatRecyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference databaseReference;                // RealtimeDB 관련 선언
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        updateChatRoom();
        inputUserData();
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

    private void updateChatRoom(){
        recyclerView_chat = findViewById(R.id.recyclerView_chat);
        chatRecyclerViewAdapter = new ChatRecyclerViewAdapter();

        ChatData item = new ChatData
                .Builder(R.drawable.loading_img,"뚱땅땅")
                .mbti("ISTP")
                .title("제목")
                .matching("매칭중")
                .build();

        chatRecyclerViewAdapter.addItem(item);
        layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView_chat.setLayoutManager(layoutManager);
        recyclerView_chat.setAdapter(chatRecyclerViewAdapter);
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

