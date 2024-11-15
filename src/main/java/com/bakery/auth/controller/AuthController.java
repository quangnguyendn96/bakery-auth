package com.bakery.auth.controller;

import com.bakery.auth.dto.UserLogin;
import com.bakery.auth.repository.jpa.UsersRepository;
import com.bakery.auth.service.UsersService;
import com.bakery.auth.Utils.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserLogin request) {

        // Xác thực người dùng
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        authenticationManager.authenticate(authenticationToken);

        // Tạo JWT nếu xác thực thành công
        String token = jwtUtils.generateToken(request.getUsername());

        // Trả về token
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserLogin userLogin) {
        if (usersRepository.findByUsername(userLogin.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        usersService.createUser(userLogin);
        return new ResponseEntity<>("Create success", HttpStatus.OK);
    }
    @PostMapping("/delete")
    public String delete(@RequestParam String userLogin) {
        usersService.deleteUser(userLogin);
        return "Delete ok";
    }
}


