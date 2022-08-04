package com.juejin.dao;

import com.juejin.proj.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  //此注解代表这是一个持久层
public interface ArticleDao {

    /**
     * 查询所有文章
     * @return 文章列表
     */
    @Select("select `id` articleId, `title` title,`intro` intro, `time` time, `author` author, `tag` tag, `visit` visit from article")
    List<Article> queryAllArticle();

    /**
     * 查询某作者所有文章
     * @param author 作者
     * @return 文章列表
     */
    @Select("select `id` articleId, `title` title,`intro` intro, `time` time, `author` author, `tag` tag, `visit` visit from article where `author`=#{author}")
    List<Article> queryAllArticleByAuthor(@Param("author")String author);

    /**
     * 根据某文章id查询文章，只有此方法返回文章内容
     * @param articleId 文章id
     * @return
     */
    @Select("select `id` articleId, `title` title,`intro` intro, `body` body, `time` time, `author` author, `tag` tag, `visit` visit from article where `id`=#{articleId}")
    Article queryArticleByArticleId(@Param("id") String articleId);

    /**
     * 查询某作者近十篇文章
     * @return 文章列表
     */
    @Select("select `id` articleId, `title` title,`intro` intro, `time` time, `author` author, `tag` tag, `visit` visit from article where `author`=#{author} order by `id` desc limit 10;")
    List<Article> queryTenArticle(@Param("author")String author);

    /**
     * 查询某作者近十篇文章
     * @return 文章列表
     */
    @Select("select `id` articleId from article where `author`=#{author} order by `id` desc limit 10;")
    List<String> queryTenArticleId(@Param("author")String author);

    /**
     * 添加文章
     * @param article 文章类
     * @return 1为添加成功
     */
    @Insert("insert into article(`id`, `title`,`intro`, `body`, `time`, `author`, `tag`, `visit`) value(#{articleId},#{title},#{intro},#{body},#{time},#{author},#{tag},#{visit})")
    int addArticle(Article article);

    /**
     * 删除文章
     * @param articleId 文章id
     * @return 1为删除成功
     */
    @Delete("delete from article where `id` = #{articleId}")
    int deleteArticleById(@Param("id")String articleId);

    /**
     * 修改文章
     * @param article 文章类
     * @return 1为修改成功
     */
    @Update("update article set `id`=#{articleId}, `title`=#{title}, `intro`={intro} `body`=#{body}, `time`=#{time}, `author`=#{author}, `tag`={tag}, `visit`={visit} where `id` = #{articleId}")
    int updateArticle(Article article);

}
