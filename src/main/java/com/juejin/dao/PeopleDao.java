package com.juejin.dao;

import com.juejin.proj.People;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface PeopleDao {

    /**
     * 根据作者id和密码查询作者
     * @param id 作者id
     * @param password 作者密码
     * @return 作者类
     */
    @Select("select `id` authorId, `nickname` nickName, `password` password, `avatar` avatar, `sid` sId from people where `id`=#{id} and `password`=#{password}")
    People queryPeopleByIdAndPwd(@Param("id")String id, @Param("password")String password);

    /**
     * 根据作者id和密码查询作者
     * @param id 作者id
     * @return 不完全的作者类
     */
    @Select("select `nickname` nickName, `avatar` avatar from people where `id`=#{id}")
    People queryPeopleById(@Param("id")String id);

    /**
     * 根据作者昵称查询
     * @param nickname 作者昵称
     * @return id
     */
    @Select("select `id` authorId from people where `nickname`=#{nickname}")
    String queryPeopleByNickname(@Param("nickname")String nickname);

    /**
     * 插入作者
     * @param people 作者类
     * @return 返回1则表示成功
     */
    @Insert("insert into people(`id`, `nickname`, `password`, `avatar`, `sid`) value(#{authorId}, #{nickName}, #{password}, #{avatar}, #{sId})")
    int insertPeople(People people);

    /**
     * 修改作者信息
     * @param people 作者类
     * @return 返回1则表示成功
     */
    @Update("update people set `nickname`=#{nickName}, `password`=#{password}, `avatar`=#{avatar} where `id`=#{authorId}")
    int updatePeopleInfo(People people);
}
