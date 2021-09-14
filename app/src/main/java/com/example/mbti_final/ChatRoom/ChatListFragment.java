package com.example.mbti_final.ChatRoom;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mbti_final.MainActivity;
import com.example.mbti_final.R;
import com.facebook.login.LoginManager;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChatListFragment extends Fragment {
    RecyclerView recyclerView_chat;
    ChatListRecyclerViewAdapter chatListRecyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;

    Button btn_logout;
    FirebaseAuth auth;
    LoginManager loginManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list,container,false);

        recyclerView_chat = view.findViewById(R.id.recyclerView_chat_list);
        chatListRecyclerViewAdapter = new ChatListRecyclerViewAdapter();

        ChatListData item = new ChatListData
                .Builder(R.drawable.loading_img,"뚱빵빵")
                .mbti("ISTP")
                .recentChat("테스트 메시지")
                .build();

        chatListRecyclerViewAdapter.addItem(item);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView_chat.setLayoutManager(layoutManager);
        recyclerView_chat.setAdapter(chatListRecyclerViewAdapter);

        btn_logout = view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(getContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        });
            }
        });

        return view;
    }
}