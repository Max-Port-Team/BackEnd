package com.juejin.service;

import com.juejin.proj.People;

import java.io.IOException;
import java.util.Map;

public interface PeopleService {

    /**
     * 登录
     * @param id 作者id
     * @param password 密码
     * @return 作者类对象
     */
    People login(String id, String password) throws IOException;

    /**
     * 注册
     * @param nickname 昵称
     * @param password 密码
     * @return 表示注册成功
     * @throws IOException
     */
    int register(String nickname, String password) throws IOException;

    /**
     * 请求作者信息
     * @param id 作者id
     * @return 作者信息的map对象
     */
    Map<String, String> getInfoById(String id) throws IOException;

    /**
     * 请求作者详细信息
     * @param id 作者id
     * @return 作者详细信息的map对象
     */
    Map<String,Object> getDetailById(String id) throws IOException;

    /**
     * 修改作者信息
     * @param people 作者类对象
     * @return 1表示成功
     */
    int UpdatePeopleInfo(People people) throws IOException;

    /**
     * 鉴权操作，通过nickname更新cookie
     * @param nickname 昵称
     * @param cookieValue cookie值
     * @return 1表示更新成功
     */
    int updateCookieByNickname(String cookieValue,String nickname) throws IOException;

    /**
     * 鉴权操作，通过cookie值查id
     * @param cookieValue cookie值
     * @return id
     */
    String getIdByCookie(String cookieValue) throws IOException;
}
