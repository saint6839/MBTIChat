package com.example.mbti_final.ChatRoom;

import android.widget.ImageView;

public class ChatData {
    private final int resId;
    private final String nickname;
    private final String mbti;
    private final String title;
    private final String matching;

    public static class Builder {
        private final int resId;
        private final String nickname;
        private String mbti=null;
        private String title=null;
        private String matching=null;

        public Builder(int resId, String nickname){
            this.resId = resId;
            this.nickname = nickname;
        }
        public Builder mbti(String val){
            mbti = val;
            return this;
        }
        public Builder title(String val){
            title = val;
            return this;
        }
        public Builder matching(String val){
            matching = val;
            return this;
        }
        public ChatData build(){
            return new ChatData(this);
        }
    }
    private ChatData(Builder builder){
        resId = builder.resId;
        nickname = builder.nickname;
        mbti = builder.mbti;
        title = builder.title;
        matching = builder.matching;
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

    public String getTitle() {
        return title;
    }

    public String getMatching() {
        return matching;
    }
}
