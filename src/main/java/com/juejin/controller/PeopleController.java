package com.juejin.controller;


import com.juejin.proj.People;
import com.juejin.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @RequestMapping(value = "/loginPeople", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> loginPeople(@RequestBody(required = false) Map<String,String> map) throws IOException {
        String id = map.get("id");
        String password = map.get("password");
        People people = peopleService.login(id,password);
        Map<String, Object> msg = new HashMap<>();
        if(people == null){
            msg.put("success",false);
            msg.put("information", "用户名或密码错误");
        }else {
            msg.put("success",true);
        }
        return msg;
    }

    @RequestMapping(value = "/registerPeople", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> registerPeople(@RequestBody(required = false) Map<String,String> map) throws IOException {
        String nickname = map.get("nickname").trim();
        String password = map.get("password").trim();
        Map<String, Object> msg = new HashMap<>();
        if(peopleService.register(nickname, password) == 1){
            msg.put("success",true);
        }else {
            msg.put("success",false);
            msg.put("information", "用户名被占用或用户名为空");
        }
        return msg;
    }

    @RequestMapping(value = "/getInfoById", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public Map<String, String> getInfoById(@RequestBody(required = false) Map<String,String> map) throws IOException {
        String id = map.get("id");
        return peopleService.getInfoById(id);
    }

    @RequestMapping(value = "/getDetailById", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> getDetailById(@RequestBody(required = false) Map<String,String> map) throws IOException {
        String id = map.get("id");
        return peopleService.getDetailById(id);
    }

    @RequestMapping(value = "/updatePeopleInfo", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> updatePeopleInfo(@RequestBody(required = false) Map<String,String> map) throws IOException {
        String id = map.get("id");
        String nickname = map.get("nickname");
        String password = map.get("password");
        String avatar = map.get("avatar");
        Map<String, Object> msg = new HashMap<>();
        if(id != null && nickname != null && password != null){
            People people = new People(id,nickname,password,avatar,null);
            if(peopleService.UpdatePeopleInfo(people) == 1){
                msg.put("success",true);
            }else {
                msg.put("success",false);
                msg.put("information","修改失败");
            }
        }else {
            msg.put("success",false);
            msg.put("information","信息不能为空");
        }
        return msg;
    }
}
