package com.learning.spring.aop.service;

import com.learning.spring.aop.annotation.AdminOnly;
import com.learning.spring.aop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{
    @Autowired
    AuthService authService;

    @Override
    public void insert(Product product){
        authService.checkAccess();
        System.out.println("insert product: " + product.getName());
    }

    @Override
    public void delete(long id){
        System.out.println("delete product: " + id);
    }

    @AdminOnly
    public void deleteWithAop(long id){
        System.out.println("delete product: " + id);
    }
}
