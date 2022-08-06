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
        List<Article> list = articleDao.queryAllArticle();
        session.commit();
        session.close();
        // 返回列表
        return list;
    }

    @Override
    public List<Article> queryAllArticleByAuthor(String author) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        List<Article> list = articleDao.queryAllArticleByAuthor(author);
        session.commit();
        session.close();
        return list;
    }

    @Override
    public Article queryAllArticleByArticleId(String ArticleId) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        Article article = articleDao.queryArticleByArticleId(ArticleId);
        session.commit();
        session.close();
        return article;
    }

    @Override
    public int addArticle(Article article) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        int flag = articleDao.addArticle(article);
        session.commit();
        session.close();
        return flag;
    }

    @Override
    public int deleteArticleById(String articleId) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        int flag = articleDao.deleteArticleById(articleId);
        session.commit();
        session.close();
        return flag;
    }

    @Override
    public int updateArticle(Article article) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        int flag = articleDao.updateArticle(article);
        session.commit();
        session.close();
        return flag;
    }
}
