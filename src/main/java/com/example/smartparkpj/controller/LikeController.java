package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.LikeDTO;
import com.example.smartparkpj.dto.ReviewDTO;
import com.example.smartparkpj.security.dto.MemberSecurityDTO;
import com.example.smartparkpj.service.LikeService;
import com.example.smartparkpj.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/like")
@Log4j2
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    private final ReviewService reviewService;

    @PreAuthorize("isAuthenticated()")//로그인 한 사람만 접근 할수 있도록
    @GetMapping("/add/{rno}/{mno}")
    public boolean like(@PathVariable int rno, @PathVariable int mno, Authentication authentication, RedirectAttributes redirectAttributes){
        log.info("좋아요 GET 테스트");

        boolean isLiked = likeService.checkLiked(rno, mno);


            if (isLiked) {
                // 이미 좋아요를 누른 경우
                likeService.remove(rno, mno);
                reviewService.like_countDown(rno);
            }
            else{
                likeService.insert(rno, mno);
                reviewService.like_count(rno);
            }

            return true;
    }
}
