package com.juejin.test;

import com.juejin.dao.ArticleDao;
import com.juejin.proj.Article;
import com.juejin.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.util.Date;

import java.io.IOException;

public class DaoTest {
    @Test
    public void test1() throws IOException {
        // 创建SqlSession对象
        SqlSession session = MyBatisUtils.openSession();
        // 获取到代理对象
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        System.out.println(articleDao.queryAllArticle());
        // 提交事务
        session.commit();
        // 关闭资源
        session.close();
    }

    @Test
    public void test2() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        Article article = new Article("1000003","JavaScriptxxx","xxxxxxxxxxxxxxxxxxxxxx",new Date(),"王五");

        System.out.println(articleDao.addArticle(article));
        session.commit();
        session.close();
    }

    @Test
    public void test3() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        System.out.println(articleDao.deleteArticleById("1000003"));
        session.commit();
        session.close();
    }


    @Test
    public void test4() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        Article article = new Article("1000002","JavaScriptxxx","xxxxxxxxxxxxxxxxxxxxxx",new Date(),"王五");
        System.out.println(articleDao.updateArticle(article));
        session.commit();
        session.close();
    }

    @Test
    public void test5() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        System.out.println(articleDao.queryAllArticleByAuthor("王五"));
        session.commit();
        session.close();
    }

    @Test
    public void test6() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        System.out.println(articleDao.queryAllArticleByArticleId("1000002"));
        session.commit();
        session.close();
    }
}

