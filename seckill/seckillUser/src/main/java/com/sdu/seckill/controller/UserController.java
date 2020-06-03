package com.sdu.seckill.controller;

import com.alibaba.fastjson.JSON;
import com.sdu.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/27
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(String username, String password, HttpServletRequest httpServletRequest){

        //1、取会员
        Map<String, Object> userMap = userService.getUser(username, password);

        //2、如果没有取到会员，写入会员
        if (!(Boolean) userMap.get("result")){
            userMap = userService.insertUser(username, password);

            if (!(Boolean) userMap.get("result")){
                return userMap;
            }
        }

        //3、写入session
        HttpSession httpSession = httpServletRequest.getSession();
        String user = JSON.toJSONString(userMap);
        httpSession.setAttribute("user", user);

        //4、返回信息
        return userMap;
    }
}
