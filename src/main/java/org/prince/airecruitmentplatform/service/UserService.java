package org.prince.airecruitmentplatform.service;

import lombok.RequiredArgsConstructor;
import org.prince.airecruitmentplatform.dto.LoginRequest;
import org.prince.airecruitmentplatform.dto.LoginResponse;
import org.prince.airecruitmentplatform.dto.RegisterRequest;
import org.prince.airecruitmentplatform.entity.User;
import org.prince.airecruitmentplatform.enums.Role;
import org.prince.airecruitmentplatform.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CANDIDATE)
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return new LoginResponse("Login Successful");
    }

}

