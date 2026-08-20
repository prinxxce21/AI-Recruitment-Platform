package org.prince.airecruitmentplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResumeResponse {

    private Long id;
    private String resumeName;
    private String filePath;
    private LocalDateTime uploadedAt;

}
