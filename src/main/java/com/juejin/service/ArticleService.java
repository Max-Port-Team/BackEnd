package com.juejin.service;

import com.juejin.proj.Article;


import java.io.IOException;
import java.util.List;

public interface ArticleService {

    /**
     * 查询所有文章
     * @return 文章列表
     */
    List<Article> queryAllArticle() throws IOException;

    /**
     * 查询某作者所有文章
     * @param author 作者
     * @return 文章列表
     */
    List<Article> queryAllArticleByAuthor(String author) throws IOException;

    /**
     * 根据某文章id查询文章
     * @param ArticleId 文章id
     * @return 文章
     */
    Article queryAllArticleByArticleId(String ArticleId) throws IOException;

    /**
     * 添加文章
     * @param article 文章类
     * @return 1为添加成功
     */
    int addArticle(Article article) throws IOException;

    /**
     * 删除文章
     * @param articleId 文章id
     * @return 1为添加成功
     */
    int deleteArticleById(String articleId) throws IOException;

    /**
     * 修改文章
     * @param article 文章类
     * @return 1为添加成功
     */
    int updateArticle(Article article) throws IOException;
}
