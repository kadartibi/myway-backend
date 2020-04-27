package com.codecool.userservice.controller;

import com.codecool.userservice.controller.dto.UserCredentials;
import com.codecool.userservice.entity.TripUser;
import com.codecool.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserCredentials tripUser, HttpServletResponse response) {
        userService.registerAllData(tripUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(tripUser.getUsername());
    }

    @GetMapping("/current-user-object/{userName}")
    public TripUser getCurrentUserObject(@PathVariable String userName) {
        return userService.getCurrentUserDetails(userName);
    }

}
