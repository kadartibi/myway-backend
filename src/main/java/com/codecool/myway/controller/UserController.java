package com.codecool.myway.controller;

import com.codecool.myway.controller.dto.UserCredentials;
import com.codecool.myway.entities.TripUser;
import com.codecool.myway.security.JwtUtil;
import com.codecool.myway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials tripUser, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                tripUser.getUsername(),
                tripUser.getPassword()
        ));
        String jwtToken = jwtUtil.generateToken(authentication);
        addTokenToCookie(response, jwtToken);
        return ResponseEntity.ok().body(tripUser.getUsername());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserCredentials tripUser) {
        userService.registerAllData(tripUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(tripUser.getUsername());
    }

    private void addTokenToCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .domain("localhost") // should be parameterized
                .sameSite("Strict")  // CSRF
//                .secure(true)
                .maxAge(Duration.ofHours(24))
                .httpOnly(true)      // XSS
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

}
