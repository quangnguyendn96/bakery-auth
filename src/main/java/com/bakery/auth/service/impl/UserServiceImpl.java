package com.bakery.auth.service.impl;

import com.bakery.auth.dto.UserLogin;
import com.bakery.auth.entity.UsersEntity;
import com.bakery.auth.repository.jpa.UsersRepository;
import com.bakery.auth.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(UserLogin userLogin) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(userLogin.getUsername());
        usersEntity.setPassword(passwordEncoder.encode(userLogin.getPassword()));
        usersEntity.setEmail(userLogin.getEmail());
        usersRepository.save(usersEntity);
    }
}
