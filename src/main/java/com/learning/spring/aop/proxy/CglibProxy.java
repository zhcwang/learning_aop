package com.learning.spring.aop.proxy;


import com.learning.spring.aop.service.ProductService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib begin");
        Object result = null;
        try {
            result = methodProxy.invokeSuper(o, args);
        } catch (Exception e) {
            System.out.println("cglib exception");
            e.printStackTrace();
        } finally {
            System.out.println("cglib finally");
        }
        return result;
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProductService.class);
        enhancer.setCallback(new CglibProxy());
        ProductService ps = (ProductService)enhancer.create();
        ps.delete(1);
    }
}
