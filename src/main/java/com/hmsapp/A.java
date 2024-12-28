package com.hmsapp;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class A {
    public static void main(String[] args) {
        //one way
        PasswordEncoder e = new BCryptPasswordEncoder();// encryption
//        System.out.println(e.encode("testing123"));
//        System.out.println(e.encode("testing123"));

        //another way
        String encodedPassword = BCrypt.hashpw("testing", BCrypt.gensalt(10));// 2^10=1024 log rounds of hashing/encryption.
        System.out.println(encodedPassword);
    }
}
