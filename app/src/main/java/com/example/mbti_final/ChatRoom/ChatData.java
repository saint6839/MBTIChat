package com.example.mbti_final.ChatRoom;

import android.widget.ImageView;

public class ChatData {
    private int resId;
    private String nickname;
    private String mbti;
    private String title;
    private String matching;

    public ChatData(){

    }

    public ChatData(int resId, String nickname, String mbti, String title, String matching) {
        this.resId = resId;
        this.nickname = nickname;
        this.mbti = mbti;
        this.title = title;
        this.matching = matching;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMatching() {
        return matching;
    }

    public void setMatching(String matching) {
        this.matching = matching;
    }
}
