package com.example.mbti_final.ChatRoom;

import android.widget.ImageView;

public class ChatData {
    private final ImageView profile;
    private final String nickname;
    private final String mbti;
    private final String title;
    private final String matching;

    public static class Builder {
        private final ImageView profile;
        private final String nickname;
        private final String mbti;
        private final String title;
        private String matching="매칭중..";

        public Builder(ImageView profile, String nickname, String mbti, String title){
            this.profile = profile;
            this.nickname = nickname;
            this.mbti = mbti;
            this.title = title;
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
        profile = builder.profile;
        nickname = builder.nickname;
        mbti = builder.mbti;
        title = builder.title;
        matching = builder.matching;
    }
}
