package com.example.mbti_final;

public class UserData {
    private final String name;
    private final String email;
    private final String nickname;
    private final String uid;

    public static class Builder{
        private String name=null;
        private String email=null;
        private String nickname=null;
        private String uid=null;

        public Builder name(String val){
            name = val;
            return this;
        }
        public Builder email(String val){
            email = val;
            return this;
        }
        public Builder nickname(String val){
            nickname = val;
            return this;
        }
        public Builder uid(String val){
            uid = val;
            return this;
        }
        public UserData build(){
            return new UserData(this);
        }
    }
    private UserData(Builder builder){
        name = builder.name;
        email = builder.email;
        nickname = builder.nickname;
        uid = builder.uid;
    }
}
