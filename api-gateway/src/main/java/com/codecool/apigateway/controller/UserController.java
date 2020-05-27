package com.codecool.apigateway.controller;

import com.codecool.apigateway.controller.dto.UserCredentials;
import com.codecool.apigateway.model.TripUser;
import com.codecool.apigateway.security.JwtUtil;
import com.codecool.apigateway.service.TripUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final TripUserService tripUserService;
    public static final String TOKEN = "token";

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials tripUser, HttpServletResponse response, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                tripUser.getUsername(),
                tripUser.getPassword()
        ));
        String jwtToken = jwtUtil.generateToken(authentication);
        addTokenToCookie(response, jwtToken);
        return ResponseEntity.ok().body(tripUser.getUsername());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserCredentials tripUser, HttpServletResponse response) {
        tripUserService.registerAllData(tripUser);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                tripUser.getUsername(),
                tripUser.getPassword()
        ));
        String jwtToken = jwtUtil.generateToken(authentication);
        addTokenToCookie(response, jwtToken);
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

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        createLogoutCookie(response);
        return ResponseEntity.ok().build();
    }

    private void createLogoutCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("token", "")
                .domain("localhost") // should be parameterized
                .sameSite("Strict")  // CSRF
//                .secure(true)
                .maxAge(0)
                .httpOnly(true)      // XSS
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    @GetMapping("/current-user/{jwtToken}")
    public String getCurrentUsername(@PathVariable String jwtToken) {
        UsernamePasswordAuthenticationToken userToken = jwtUtil
                .validateTokenAndExtractUserSpringToken(jwtToken);
        return userToken == null ? "" : userToken.getName();
    }

    @GetMapping("/current-user-object")
    public TripUser getCurrentUserObject(HttpServletRequest request) {
        Optional<Cookie> jwtToken =
                Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[]{}))
                        .filter(cookie -> cookie.getName().equals(TOKEN))
                        .findFirst();
        if (jwtToken.isPresent()) {
            UsernamePasswordAuthenticationToken userToken = jwtUtil
                    .validateTokenAndExtractUserSpringToken(jwtToken.get().getValue());
            return tripUserService.findById(userToken.getName());
        }
        return null;
    }

}
