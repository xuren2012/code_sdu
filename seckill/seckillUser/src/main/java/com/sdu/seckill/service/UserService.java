package com.sdu.seckill.service;

import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/27
 */
public interface UserService {

    public Map<String, Object> getUser(String username, String password);

    public Map<String, Object> insertUser(String username, String password);
}
