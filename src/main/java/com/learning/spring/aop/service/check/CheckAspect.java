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
    public void adminOnly(){}
    @Pointcut("within(com.learning.spring.aop.service.*)")
    public void pointWithin(){ }
    @Pointcut("this(com.learning.spring.aop.service.ProductService)")
    public void pointThis(){}
    @Pointcut("target(com.learning.spring.aop.service.IProductService)")
    public void pointTarge(){}
    @Pointcut("bean(*Service)")
    public void pointBean(){}
    @Pointcut("execution(* *..find*(Long))")
    public void pointExecution(){}
    @Pointcut("args(Long)")
    public void pointArg(){}
    @Pointcut("args(Long,..)")
    public void pointArgs(){}

    @Before("adminOnly()")
    public void check(){
        authService.checkAccess();
    }
    @Before("pointWithin()")
    public void within(){
        System.out.println("within");
    }
    @Before("pointThis()")
    public void aopThis(){
        System.out.println("this");
    }
    @Before("pointTarge()")
    public void target(){
        System.out.println("within");
    }
    @Before("pointBean()")
    public void bean(){
        System.out.println("target");
    }
    @Before("pointExecution()")
    public void execution(){
        System.out.println("target");
    }
    @Before("pointArg()")
    public void arg(){
        System.out.println("target");
    }
    @Before("pointArgs()")
    public void args(){
        System.out.println("target");
    }


}
