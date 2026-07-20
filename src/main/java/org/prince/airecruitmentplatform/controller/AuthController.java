package org.prince.airecruitmentplatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.prince.airecruitmentplatform.dto.RegisterRequest;
import org.prince.airecruitmentplatform.entity.User;
import org.prince.airecruitmentplatform.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }
    // The above code is done by using lombok ->@RequiredArgsConstructor

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterRequest request) {

        return userService.register(request);

    }

}
