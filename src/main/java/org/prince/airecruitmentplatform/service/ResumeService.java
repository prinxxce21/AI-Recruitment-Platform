package org.prince.airecruitmentplatform.service;

import lombok.RequiredArgsConstructor;
import org.prince.airecruitmentplatform.dto.ResumeResponse;
import org.prince.airecruitmentplatform.entity.Resume;
import org.prince.airecruitmentplatform.entity.User;
import org.prince.airecruitmentplatform.repository.ResumeRepository;
import org.prince.airecruitmentplatform.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeResponse uploadResume(MultipartFile file) throws IOException {

//        Gets the upladed fileName.
        String fileName = file.getOriginalFilename();

//        Gets the current Authentication.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        Gets the authenticated user Details.
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

//        Gets the email we use as the username.
        String email = userDetails.getUsername();

//        Gets our actual user entity.
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));

//        Creates the resume object
        Resume resume = Resume.builder()
                .resumeName(fileName)
                .user(user)
                .build();

        Path uploadPath = Paths.get("uploads/resumes");
        Files.createDirectories(uploadPath);

        Path savedFilePath = uploadPath.resolve(fileName);
        file.transferTo(savedFilePath);

        resume.setFilePath(savedFilePath.toString());

        Resume savedResume = resumeRepository.save(resume);
//        save the file and then it's metadata.

        return new ResumeResponse(
                savedResume.getId(),
                savedResume.getResumeName(),
                savedResume.getFilePath(),
                savedResume.getUploadedAt()
        );
    }

}
