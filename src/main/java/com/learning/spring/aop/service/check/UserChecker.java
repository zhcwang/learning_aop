package com.learning.spring.aop.service.check;

import java.util.Optional;

public class UserChecker {

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static String get(){
        return Optional.ofNullable(holder.get()).orElse("undefine");
    }

    public static void set(String user){
        holder.set(user);
    }
}
