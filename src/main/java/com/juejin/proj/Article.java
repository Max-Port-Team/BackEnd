package com.juejin.proj;

import java.util.Date;

/**
 * 文章类
 */
public class Article {

    private String articleId; //文章id
    private String title; //文章题目
    private String intro; //文章简介
    private String body; //文章主体
    private Date time; //日期
    private String author; //作者
    private String tag;//文章标签类型
    private int visit;//文章访问量

    public Article() {
    }

    public Article(String articleId, String title, String intro, String body, Date time, String author, String tag, int visit) {
        this.articleId = articleId;
        this.title = title;
        this.intro = intro;
        this.body = body;
        this.time = time;
        this.author = author;
        this.tag = tag;
        this.visit = visit;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId='" + articleId + '\'' +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", body='" + body + '\'' +
                ", time=" + time +
                ", author='" + author + '\'' +
                ", tag='" + tag + '\'' +
                ", visit=" + visit +
                '}';
    }
}
