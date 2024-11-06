package com.bakery.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLogin {
    @NotNull
    private String username;
    private String password;
    private String email;
    private String address;
}
