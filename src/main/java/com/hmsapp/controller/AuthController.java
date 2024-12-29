package com.hmsapp.controller;

import com.hmsapp.entity.User;
import com.hmsapp.payload.JwtToken;
import com.hmsapp.payload.LoginDto;
import com.hmsapp.payload.ProfileDTO;
import com.hmsapp.payload.UserDto;
import com.hmsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    //used for user signup
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> createUser(
            @RequestBody UserDto userDto
    ){
         UserDto savedUserDto = userService.createUser(userDto);
         return new ResponseEntity<>(savedUserDto , HttpStatus.CREATED);
    }

    //used for property owner signup
    @PostMapping("/property/sign-up")
    public ResponseEntity<UserDto> createPropertyOwner(
            @RequestBody UserDto userDto
    ){
         UserDto savedUserDto = userService.createPropertyOwner(userDto);
         return new ResponseEntity<>(savedUserDto , HttpStatus.CREATED);
    }

    //used for blogger sign-up
    @PostMapping("/blog/sign-up")
    public ResponseEntity<UserDto> createBlogManager(
            @RequestBody UserDto userDto
    ){
        UserDto savedUserDto = userService.createBlogManager(userDto);
        return new ResponseEntity<>(savedUserDto , HttpStatus.CREATED);
    }

    //common-login
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginDto loginDto
    ){
        String token = userService.verifyLogin(loginDto);

        JwtToken jwtToken = new JwtToken();

        jwtToken.setToken(token);
        jwtToken.setType("JWT");

        if(token!=null){
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid username/password", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<ProfileDTO> getUserProfile(@AuthenticationPrincipal User user){

        ProfileDTO profile = userService.getUserProfile(user);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
