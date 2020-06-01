package com.sdu.sell.utils;

import com.sdu.sell.enums.CodeEnum;

/**
 * @Description:
 *              获取类型信息
 * @Author: YTF
 * @Date: 2020/5/8
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T enumConstant : enumClass.getEnumConstants()) {
            if (code.equals(enumConstant.getCode())) {
                return enumConstant;
            }
        }
        return null;
    }
}
