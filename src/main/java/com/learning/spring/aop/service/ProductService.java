package com.learning.spring.aop.service;

import com.learning.spring.aop.annotation.AdminOnly;
import com.learning.spring.aop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    AuthService authService;

    public void insert(Product product){
        authService.checkAccess();
        System.out.println("insert product: " + product.getName());
    }
    public void delete(long id){
        authService.checkAccess();
        System.out.println("delete product: " + id);
    }

    @AdminOnly
    public void deleteWithAop(long id){
        System.out.println("delete product: " + id);
    }
}
