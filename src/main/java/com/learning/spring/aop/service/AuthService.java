package com.learning.spring.aop.service;

import com.learning.spring.aop.service.check.UserChecker;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    public void checkAccess(){
        String user = UserChecker.get();
        if(!"admin".equals(user)){
            throw new RuntimeException("operation not allow!");
        }
    }

}
