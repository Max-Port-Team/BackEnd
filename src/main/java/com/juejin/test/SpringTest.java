package com.juejin.test;

import com.juejin.service.ArticleService;
import com.juejin.service.impl.ArticleServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class SpringTest {

    private ArticleService articleService = new ArticleServiceImpl();

    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ArticleService as = (ArticleService) ac.getBean("ArticleService");
    }

    @Test
    public void test2() throws IOException {
        System.out.println(articleService.queryAllArticle());
    }
}
