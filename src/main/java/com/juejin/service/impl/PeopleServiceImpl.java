package com.juejin.service.impl;

import com.juejin.dao.ArticleDao;
import com.juejin.dao.PeopleDao;
import com.juejin.proj.People;
import com.juejin.service.PeopleService;
import com.juejin.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Molly
 * @create 2022-08-02-21:47
 */
@Service("PeopleService")
public class PeopleServiceImpl implements PeopleService {


    @Override
    public People login(String id, String password) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        PeopleDao peopleDao = session.getMapper(PeopleDao.class);
        return peopleDao.queryPeopleByIdAndPwd(id,password);
    }

    @Override
    public int register(String nickname, String password) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        PeopleDao peopleDao = session.getMapper(PeopleDao.class);
        if(peopleDao.queryPeopleByNickname(nickname) == null){
            People people = new People(null,nickname,password,null,null);
            return peopleDao.insertPeople(people);
        }else {
            return 0;
        }
    }

    @Override
    public Map<String, String> getInfoById(String id) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        PeopleDao peopleDao = session.getMapper(PeopleDao.class);
        People people = peopleDao.queryPeopleById(id);
        Map<String, String> map = new HashMap<>();
        map.put("nickname",people.getNickName());
        map.put("avatar",people.getAvatar());
        return map;
    }

    @Override
    public Map<String, Object> getDetailById(String id) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        PeopleDao peopleDao = session.getMapper(PeopleDao.class);
        ArticleDao articleDao = session.getMapper(ArticleDao.class);
        People people = peopleDao.queryPeopleById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("nickname",people.getNickName());
        map.put("avatar",people.getAvatar());
        map.put("articles",articleDao.queryTenArticle(id));
        map.put("articleIds",articleDao.queryTenArticleId(id));
        return map;
    }

    @Override
    public int UpdatePeopleInfo(People people) throws IOException {
        SqlSession session = MyBatisUtils.openSession();
        PeopleDao peopleDao = session.getMapper(PeopleDao.class);
        return peopleDao.updatePeopleInfo(people);
    }
}
