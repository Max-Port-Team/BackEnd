package com.juejin.service.impl;

import com.juejin.dao.ArticleDao;
import com.juejin.proj.Article;
import com.juejin.service.ArticleService;
import com.juejin.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Override
    public List<Article> queryAllArticle() throws IOException {
        // 创建SqlSession对象
        SqlSession session = MyBatisUtils.openSession();
        // 获取到代理对象
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        // 返回列表
        return articleDao.queryAllArticle();
    }

    @Override
    public List<Article> queryAllArticleByAuthor(String author) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        return articleDao.queryAllArticleByAuthor(author);
    }

    @Override
    public Article queryAllArticleByArticleId(String ArticleId) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        return articleDao.queryAllArticleByArticleId(ArticleId);
    }

    @Override
    public int addArticle(Article article) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        return articleDao.addArticle(article);
    }

    @Override
    public int deleteArticleById(String articleId) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        return articleDao.deleteArticleById(articleId);
    }

    @Override
    public int updateArticle(Article article) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        return articleDao.updateArticle(article);
    }
}
