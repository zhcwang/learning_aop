package com.learning.spring.aop.proxy;

import com.learning.spring.aop.service.IProductService;
import com.learning.spring.aop.service.ProductService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

    private ProductService realService;

    public JdkProxy(ProductService realService) {
        this.realService = realService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Begin");
        Object result;
        try {
            result = method.invoke(realService, args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception");
            throw e;
        } finally {
            System.out.println("Finally");
        }
        return result;
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IProductService productService = (IProductService)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IProductService.class}, new JdkProxy(new ProductService()));
        productService.delete(1);
    }

}
