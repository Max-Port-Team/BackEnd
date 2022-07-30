package com.juejin.proj;

import java.util.Date;

/**
 * 文章类
 */
public class Article {

    private String articleId; //文章id
    private String title; //文章题目
    private String body; //文章主体
    private Date time; //日期
    private String author; //作者

    public Article() {
    }

    public Article(String articleId, String title, String body, Date time, String author) {
        this.articleId = articleId;
        this.title = title;
        this.body = body;
        this.time = time;
        this.author = author;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId='" + articleId + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", time=" + time +
                ", author='" + author + '\'' +
                '}';
    }
}
