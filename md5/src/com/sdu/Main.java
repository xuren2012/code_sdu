package com.sdu;

import com.sdu.util.MD5Utils;

public class Main {

    public static void main(String[] args) {

        String password = "123";
        String passwordMD5 = MD5Utils.MD5Encode(password, "utf8");
        System.out.println(passwordMD5);
        System.out.println("============================");


    }
}
