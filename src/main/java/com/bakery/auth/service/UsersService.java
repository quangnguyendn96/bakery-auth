package com.bakery.auth.service;

import com.bakery.auth.dto.UserLogin;

public interface UsersService {
    void createUser(UserLogin userLogin);

    void deleteUser(String username);

}
