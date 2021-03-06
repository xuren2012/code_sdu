package com.sdu.sell.utils;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/6
 */
public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较支付金额是否一致，精确度为0.01
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }
}
