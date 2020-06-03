package com.sdu.seckill.service;

import com.sdu.seckill.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> getUser(String username, String password) {
        Map<String, Object> resultMap = new HashMap<>();

        //1、判断传入的参数
        if (username == null || username.equals("")) {
            resultMap.put("result", false);
            resultMap.put("msg", "用户名不能为空！");
            return resultMap;
        }

        //2、调userdao
        ArrayList<Map<String, Object>> list = userDao.getUser(username, password);

        //3、如果没有查询到返回错误信息
        if (list == null || list.size() == 0) {
            resultMap.put("result", false);
            resultMap.put("msg", "没有查询到数据！");
            return resultMap;
        }

        //4、返回正常信息
        resultMap = list.get(0);
        resultMap.put("result", true);
        resultMap.put("msg", "");
        return resultMap;
    }

    @Override
    public Map<String, Object> insertUser(String username, String password) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        //1、判断传入的参数
        if (username == null || username.equals("")) {
            resultMap.put("result", false);
            resultMap.put("msg", "用户名不能为空！");
            return resultMap;
        }

        //2、调用方法
        int new_id = userDao.insertUser(username, password);

        //3、执行不成功，返回信息
        if (new_id <= 0) {
            resultMap.put("result", false);
            resultMap.put("msg", "数据库执行不成功");
            return resultMap;
        }

        //4、返回正常信息
        resultMap.put("user_id", new_id);
        resultMap.put("username", username);
        resultMap.put("phone", username);
        resultMap.put("password", password);
        resultMap.put("result", true);
        resultMap.put("msg", "");
        return resultMap;
    }
}
