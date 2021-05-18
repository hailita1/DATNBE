package com.example.demo.service.user;

import com.example.demo.model.auth.User;
import com.example.demo.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByEmail(String email);
}
