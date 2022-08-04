package com.juejin.test;

import com.juejin.dao.ArticleDao;
import com.juejin.dao.PeopleDao;
import com.juejin.proj.Article;
import com.juejin.proj.People;
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
        Article article = new Article("1000003","JavaScriptxxx","略","xxxxxxxxxxxxxxxxxxxxxx",new Date(),"王五","前端",0);
        System.out.println(articleDao.addArticle(article));
        session.commit();
        session.close();
    }

    @Test
    public void test3() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        System.out.println(articleDao.deleteArticleById("0000000002"));
        session.commit();
        session.close();
    }


    @Test
    public void test4() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        Article article = new Article("1000002","JavaScriptxxx","略","xxxxxxxxxxxxxxxxxxxxxx",new Date(),"王五","前端",0);
        System.out.println(articleDao.updateArticle(article));
        session.commit();
        session.close();
    }

    @Test
    public void test5() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        System.out.println(articleDao.queryAllArticleByAuthor("0000000003"));
        session.commit();
        session.close();
    }

    @Test
    public void test6() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        System.out.println(articleDao.queryArticleByArticleId("0000000001"));
        session.commit();
        session.close();
    }

    @Test
    public void test7() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        System.out.println(articleDao.queryTenArticle("0000000006"));
        session.commit();
        session.close();
    }

    @Test
    public void test8() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        PeopleDao PeopleDao = session.getMapper(PeopleDao.class);
        System.out.println(PeopleDao.queryPeopleByIdAndPwd("0000000001", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92"));
        session.commit();
        session.close();
    }

    @Test
    public void test9() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        PeopleDao PeopleDao = session.getMapper(PeopleDao.class);
        System.out.println(PeopleDao.queryPeopleById("0000000001"));
        session.commit();
        session.close();
    }

    @Test
    public void test10() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        PeopleDao PeopleDao = session.getMapper(PeopleDao.class);
        People people = new People(null,"test","123123",null,null);
        System.out.println(PeopleDao.insertPeople(people));
        session.commit();
        session.close();
    }

    @Test
    public void test11() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        System.out.println(articleDao.queryTenArticleId("0000000006"));
        session.commit();
        session.close();
    }

    @Test
    public void test12() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        PeopleDao PeopleDao = session.getMapper(PeopleDao.class);
        System.out.println(PeopleDao.queryPeopleByNickname("111"));
        session.commit();
        session.close();
    }

    @Test
    public void test13() throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        PeopleDao PeopleDao = session.getMapper(PeopleDao.class);
        People people = new People("5","Edwin","8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92",null,null);
        System.out.println(PeopleDao.updatePeopleInfo(people));
        session.commit();
        session.close();
    }
}

