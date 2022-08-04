package com.juejin.proj;

/**
 * 作者类
 */
public class People {
    private String authorId; //作者id
    private String nickName; //作者昵称
    private String password; //作者账号密码
    private String avatar; //作者头像图标
    private String sId;// 存储cookie

    public People() {
    }

    public People(String authorId, String nickName, String password, String avatar, String sId) {
        this.authorId = authorId;
        this.nickName = nickName;
        this.password = password;
        this.avatar = avatar;
        this.sId = sId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    @Override
    public String toString() {
        return "People{" +
                "authorId='" + authorId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sId='" + sId + '\'' +
                '}';
    }
}
