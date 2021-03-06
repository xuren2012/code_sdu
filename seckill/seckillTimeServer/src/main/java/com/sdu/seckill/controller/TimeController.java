package com.sdu.seckill.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/26
 */
@RestController
public class TimeController {

    @RequestMapping(value="/getTime")
    public String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
