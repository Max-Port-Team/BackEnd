package com.juejin.dao;

import com.juejin.proj.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  //此注解代表这是一个持久层
public interface ArticleDao {

    /**
     * 查询所有文章
     * @return 文章列表
     */
    @Select("select `article_id` articleId, `title` title, `body` body, `time` time, `author` author from article")
    List<Article> queryAllArticle();

    /**
     * 查询某作者所有文章
     * @param author 作者
     * @return 文章列表
     */
    @Select("select `article_id` articleId, `title` title, `body` body, `time` time, `author` author from article where `author`=#{author}")
    List<Article> queryAllArticleByAuthor(String author);

    /**
     * 根据某文章id查询文章
     * @param ArticleId 文章id
     * @return
     */
    @Select("select `article_id` articleId, `title` title, `body` body, `time` time, `author` author from article where `article_id`=#{articleId}")
    Article queryAllArticleByArticleId(String ArticleId);

    /**
     * 添加文章
     * @param article 文章类
     * @return 1为添加成功
     */
    @Insert("insert into article(`article_id`,`title`,`body`,`time`,`author`) value(#{articleId},#{title},#{body},#{time},#{author})")
    int addArticle(Article article);

    /**
     * 删除文章
     * @param articleId 文章id
     * @return 1为删除成功
     */
    @Delete("delete from article where `article_id` = #{articleId}")
    int deleteArticleById(String articleId);

    /**
     * 修改文章
     * @param article 文章类
     * @return 1为修改成功
     */
    @Update("update article set `article_id`=#{articleId}, `title`=#{title}, `body`=#{body}, `time`=#{time}, `author`=#{author} where `article_id` = #{articleId}")
    int updateArticle(Article article);
}
