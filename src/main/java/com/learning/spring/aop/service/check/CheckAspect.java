package com.learning.spring.aop.service.check;

import com.learning.spring.aop.service.AuthService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CheckAspect {

    @Autowired
    AuthService authService;

    @Pointcut("@annotation(com.learning.spring.aop.annotation.AdminOnly)")
    public void adminOnly(){

    }

    @Before("adminOnly()")
    public void check(){
        authService.checkAccess();
    }
}
