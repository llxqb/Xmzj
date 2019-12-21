package com.xmzj.entity.response;

public class PersonalInfoResponse {
    /**
     * nickname : 132****3731
     * phoneNum : 13262253731
     * avatar : https://www.xinmizj.com/res/member/avatar/default.jpg
     * account : llxqb
     * email :
     */

    private String nickname;
    private String phoneNum;
    private String avatar;
    private String account;
    private String email;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
