package com.sdu.springbootmd5;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class SpringbootMd5ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testMd5(){
        String password = "123";
        String smd5 = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(smd5);
    }

}
