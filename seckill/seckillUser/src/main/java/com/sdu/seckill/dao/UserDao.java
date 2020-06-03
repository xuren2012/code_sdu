package com.sdu.seckill.dao;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/27
 */
public interface UserDao {

    public ArrayList<Map<String, Object>> getUser(String username, String password);

    public int insertUser(String username, String password);

}
