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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final TripUserService tripUserService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials tripUser, HttpServletResponse response) {
        System.out.println("Before authentication");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                tripUser.getUsername(),
                tripUser.getPassword()
        ));
        System.out.println(authentication.toString());
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
        System.out.println("Test in login controller");
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

    @GetMapping("/current-user")
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal().toString();
    }

    @GetMapping("/current-user-object/{userName}")
    public TripUser getCurrentUserObject(@PathVariable String userName) {
        return tripUserService.getCurrentUserDetails(userName).orElse(null);
    }

    @GetMapping("/test")
    public Optional<TripUser> test() {
        return tripUserService.findById("user");
    }

}
