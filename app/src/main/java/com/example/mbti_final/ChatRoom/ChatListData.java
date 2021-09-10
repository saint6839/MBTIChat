package com.example.mbti_final.ChatRoom;

public class ChatListData {
    private final int resId;
    private final String nickname;
    private final String mbti;
    private final String recentChat;

    public static class Builder{
        private final int resId;
        private final String nickname;
        private String mbti;
        private String recentChat;

        public Builder(int resId, String nickname){
            this.resId = resId;
            this.nickname = nickname;
        }
        public Builder mbti(String val){
            mbti = val;
            return this;
        }
        public Builder recentChat(String val){
            recentChat = val;
            return this;
        }
        public ChatListData build(){
            return new ChatListData(this);
        }
    }
    private ChatListData(Builder builder){
        resId = builder.resId;
        nickname = builder.nickname;
        mbti = builder.mbti;
        recentChat = builder.recentChat;
    }

    public int getResId() {
        return resId;
    }
    public String getNickname() {
        return nickname;
    }
    public String getMbti() {
        return mbti;
    }
    public String getRecentChat() {
        return recentChat;
    }
}
