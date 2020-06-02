package com.sdu.sha;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/6/2
 */
@SpringBootTest(classes = SHATest.class)
@RunWith(SpringRunner.class)
class SHATest {

    @Test
    public void test(){
        try {
            String inputStr = "简单加密";
            System.out.println(SHA.getResult(inputStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}