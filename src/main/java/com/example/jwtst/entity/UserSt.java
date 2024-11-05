package com.example.jwtst.entity;

// Lớp User đại diện cho thông tin của một người dùng
public class UserSt {
    private String username;
    private String password;

    // Constructor, getter và setter
    public UserSt(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

// UserService sẽ thực hiện xác thực người dùng
