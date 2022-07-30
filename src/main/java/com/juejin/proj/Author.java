package com.juejin.proj;

/**
 * 作者类
 */
public class Author {
    private String AuthorId; //作者id
    private String NickName; //作者昵称
    private String password; //作者账号密码

    public Author() {
    }

    public Author(String authorId, String nickName, String password) {
        AuthorId = authorId;
        NickName = nickName;
        this.password = password;
    }

    public String getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(String authorId) {
        AuthorId = authorId;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Author{" +
                "AuthorId='" + AuthorId + '\'' +
                ", NickName='" + NickName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
