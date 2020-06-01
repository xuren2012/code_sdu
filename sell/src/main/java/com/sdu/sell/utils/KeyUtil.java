package com.sdu.sell.utils;

import java.util.Random;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/4/24
 */
public class KeyUtil {

    /**
     * 生成唯一主键：
     *      时间+随机数
     * @return
     */
    public static String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis()+String.valueOf(number);
    }
}
