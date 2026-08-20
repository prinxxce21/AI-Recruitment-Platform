package org.prince.airecruitmentplatform.controller;

import lombok.RequiredArgsConstructor;
import org.prince.airecruitmentplatform.dto.ResumeResponse;
import org.prince.airecruitmentplatform.entity.Resume;
import org.prince.airecruitmentplatform.service.ResumeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResumeResponse uploadResume(@RequestParam("file")MultipartFile file) throws IOException {
        return resumeService.uploadResume(file);
    }

    @GetMapping
    public List<ResumeResponse> getResumes() {
        return resumeService.getMyResumes();
    }
}

