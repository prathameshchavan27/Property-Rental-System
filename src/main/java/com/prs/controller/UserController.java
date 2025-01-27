package com.prs.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.prs.dao.UserDao;
import com.prs.dto.LoginRequest;
import com.prs.dto.LoginResponse;
import com.prs.dto.RequestDTO;
import com.prs.pojos.Role;
import com.prs.pojos.User;
import com.prs.security.JwtUtil;

@RestController
//@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    
    
    @GetMapping("/register")
    public Role[] getRoles() {
    	return Role.values();
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return "User registered successfully!";
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = userDao.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user.getId());
        if (passwordEncoder.matches(password, user.getPassword())) {
        	Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("token", jwtUtil.generateToken(user.getEmail(), user.getRole().name()));
            return ResponseEntity.status(HttpStatus.OK).body(response);
            
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
    
   

    
    
//    
//    @PutMapping("/users/{id}")
//    public String updateUser(@RequestBody User user) {
//    	return userDao.sa
//    }

}

