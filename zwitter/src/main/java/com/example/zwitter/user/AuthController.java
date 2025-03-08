package com.example.zwitter.user;

import com.example.zwitter.dto.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/user/register")
    public User registerUser(@RequestBody RegisterUser registerUser) {
        return authenticationService.registerUser(registerUser.userName(), registerUser.fullName(), registerUser.email(), registerUser.password(), registerUser.biography());
    }

    @PostMapping("/admin/register")
    public User registerAdmin(@RequestBody RegisterUser registerUser) {
        return authenticationService.registerAdmin(registerUser.userName(), registerUser.fullName(), registerUser.email(), registerUser.password(), registerUser.biography());
    }
}
