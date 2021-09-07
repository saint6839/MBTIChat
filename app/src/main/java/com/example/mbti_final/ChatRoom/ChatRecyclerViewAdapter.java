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

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ChatData> items = new ArrayList<>();

    public ArrayList<ChatData> getItems() {
        return items;
    }

    public void setItems(ArrayList<ChatData> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_profile;
        TextView txt_nickname;
        TextView txt_mbti;
        TextView txt_title;
        TextView txt_matching;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_profile= itemView.findViewById(R.id.img_profile);
            txt_nickname = itemView.findViewById(R.id.txt_nickname);
            txt_mbti = itemView.findViewById(R.id.txt_mbti);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_matching = itemView.findViewById(R.id.txt_matching);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = new ViewHolder(holder.itemView);
        ChatData chatData = items.get(position);
        viewHolder.img_profile.setImageResource(chatData.getResId());
        viewHolder.txt_nickname.setText(chatData.getNickname());
        viewHolder.txt_mbti.setText(chatData.getMbti());
        viewHolder.txt_title.setText(chatData.getTitle());
        viewHolder.txt_matching.setText(chatData.getMatching());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ChatData item){
        items.add(item);
    }
}

