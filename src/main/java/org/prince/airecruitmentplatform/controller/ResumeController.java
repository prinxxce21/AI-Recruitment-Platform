package org.prince.airecruitmentplatform.controller;

import lombok.RequiredArgsConstructor;
import org.prince.airecruitmentplatform.entity.Resume;
import org.prince.airecruitmentplatform.service.ResumeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public Resume uploadResume(@RequestParam("file")MultipartFile file) throws IOException {
        return resumeService.uploadResume(file);
    }
}

