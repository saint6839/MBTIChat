package com.example.mbti_final.ChatRoom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mbti_final.R;

public class ChatRoomFragment extends Fragment {
    RecyclerView recyclerView_chat;
    ChatRecyclerViewAdapter chatRecyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_room,container,false);

        recyclerView_chat = view.findViewById(R.id.recyclerView_chat);
        chatRecyclerViewAdapter = new ChatRecyclerViewAdapter();

        ChatData item = new ChatData
                .Builder(R.drawable.loading_img,"뚱땅땅")
                .mbti("ISTP")
                .title("제목")
                .matching("매칭중")
                .build();

        chatRecyclerViewAdapter.addItem(item);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView_chat.setLayoutManager(layoutManager);
        recyclerView_chat.setAdapter(chatRecyclerViewAdapter);

        return view;
    }
}