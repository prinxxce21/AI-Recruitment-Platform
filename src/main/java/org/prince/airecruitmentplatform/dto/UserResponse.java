package org.prince.airecruitmentplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.prince.airecruitmentplatform.enums.Role;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;

}
