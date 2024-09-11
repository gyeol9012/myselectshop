package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.FolderRequestDto;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {

    private FolderService folderService;

    @PostMapping("/folders")
    public void addFolders(@RequestParam FolderRequestDto folderRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<String> folderNames = folderRequestDto.getFolderNames();

        folderService.addFolders(folderNames, userDetails.getUser());
    }

}
