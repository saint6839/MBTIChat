package com.example.mbti_final.ChatRoom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mbti_final.R;

public class ChatListFragment extends Fragment {
    RecyclerView recyclerView_chat;
    ChatListRecyclerViewAdapter chatListRecyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;

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

        return view;
    }
}