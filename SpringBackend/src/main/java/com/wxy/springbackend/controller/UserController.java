package com.wxy.springbackend.controller;

import com.wxy.springbackend.model.User;
import com.wxy.springbackend.repository.UserRepository;
import com.wxy.springbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        boolean success = userService.register(username, password);
        return success ? "Register Success" : "Register Fail, Username exists or illegal";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password) {
        boolean success = userService.login(username, password);
        Map<String, Object> response = new HashMap<>();

        if(success){
            User user = userRepository.findByUsername(username);
            response.put("status", "success");
            response.put("userId", user.getId());
            return ResponseEntity.ok(response);
        }else{
            response.put("status", "fail");
            response.put("userId", "Username or password incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/user/change_password")
    public String changePassword(@RequestParam Integer userid, @RequestParam String oldPassword, @RequestParam String newPassword) {
        boolean success = userService.resetPassword(userid, oldPassword, newPassword);
        return success ? "Reset Password Success" : "Reset Fail, Incorrect Old Password";
    }

    @PostMapping("/user/change_information")
    public User changeInformation(@RequestBody User user) {
        return userService.changeInformation(user);
    }

    @PostMapping("/user/info")
    public User getUserInfo(@RequestParam Integer userId) {
        return userService.getUserInfoById(userId);
    }

}
