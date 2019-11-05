package com.learning.spring.aop.service;

import com.learning.spring.aop.service.check.UserChecker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test(expected = Exception.class)
    public void testAnnoInsertTest(){
        UserChecker.set("tom");
        productService.delete(1);
    }

    @Test
    public void testInsertTest(){
        UserChecker.set("admin");
        productService.delete(1);
    }

    @Test
    public void deleteWithAop() {
        UserChecker.set("admin");
        productService.delete(1);
    }
}