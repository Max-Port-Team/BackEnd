package com.juejin.controller;

import com.juejin.proj.Article;
import com.juejin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/queryAllArticle", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public List<Article> queryAllArticle() throws IOException {
        return articleService.queryAllArticle();
    }

    @RequestMapping(value = "/queryAllArticleByAuthor", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public List<Article> queryAllArticleByAuthor(@RequestBody(required = false) String authorId) throws IOException {
        return articleService.queryAllArticleByAuthor(authorId);
    }

    @RequestMapping(value = "/queryAllArticleByArticleId", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public Article queryAllArticleByArticleId(@RequestBody(required = false) String ArticleId) throws IOException {
        return articleService.queryAllArticleByArticleId(ArticleId);
    }

    @RequestMapping(value = "/addArticle", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> addArticle(@RequestBody(required = false) Map<String,String> map) throws IOException {
        String title = map.get("title");
        String body = map.get("body");
        Date time = new Date();
        String author = map.get("author");
        String articleId = System.currentTimeMillis() + author;
        Article article = new Article(articleId,title,body,time,author);
        Map<String, Object> mapMsg = new HashMap<>();
        //若添加成功，则返回1
        if(articleService.addArticle(article) == 1){
            mapMsg.put("success",true);
            return mapMsg;
        }else {
            mapMsg.put("false",false);
            return mapMsg;
        }
    }

    @RequestMapping(value = "/deleteArticle", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> deleteArticle(@RequestBody(required = false) String articleId) throws IOException {
        Map<String, Object> mapMsg = new HashMap<>();
        if(articleService.deleteArticleById(articleId) == 1){
            mapMsg.put("success",true);
            return mapMsg;
        }else {
            mapMsg.put("false",false);
            return mapMsg;
        }
    }

    @RequestMapping(value = "/updateArticle", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> updateArticle(@RequestBody(required = false) Map<String,String> map) throws IOException {
        String title = map.get("title");
        String body = map.get("body");
        Date time = new Date();
        String author = map.get("author");
        String articleId = System.currentTimeMillis() + author;
        Article article = new Article(articleId,title,body,time,author);
        Map<String, Object> mapMsg = new HashMap<>();
        //若添加成功，则返回1
        if(articleService.updateArticle(article) == 1){
            mapMsg.put("success",true);
            return mapMsg;
        }else {
            mapMsg.put("false",false);
            return mapMsg;
        }
    }
}
