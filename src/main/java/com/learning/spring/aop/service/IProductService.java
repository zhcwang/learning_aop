package com.learning.spring.aop.service;

import com.learning.spring.aop.domain.Product;

public interface IProductService {
    void insert(Product product);

    void delete(long id);
}
