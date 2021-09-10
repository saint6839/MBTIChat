package com.example.mbti_final.ChatRoom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mbti_final.R;

import java.util.ArrayList;

public class ChatListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ChatListData> items = new ArrayList<>();

    public ArrayList<ChatListData> getItems() {
        return items;
    }

    public void setItems(ArrayList<ChatListData> items) {
        this.items = items;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_profile;
        TextView txt_nickname;
        TextView txt_mbti;
        TextView txt_chat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_profile= itemView.findViewById(R.id.img_profile);
            txt_nickname = itemView.findViewById(R.id.txt_nickname);
            txt_mbti = itemView.findViewById(R.id.txt_mbti);
            txt_chat = itemView.findViewById(R.id.txt_chat);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = new ViewHolder(holder.itemView);
        ChatListData chatlistData = items.get(position);
        viewHolder.img_profile.setImageResource(chatlistData.getResId());
        viewHolder.txt_nickname.setText(chatlistData.getNickname());
        viewHolder.txt_mbti.setText(chatlistData.getMbti());
        viewHolder.txt_chat.setText(chatlistData.getRecentChat());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ChatListData item){
        items.add(item);
    }
}

