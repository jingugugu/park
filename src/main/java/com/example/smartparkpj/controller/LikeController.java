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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/like")
@Log4j2
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    private final ReviewService reviewService;

    @PreAuthorize("isAuthenticated()")//로그인 한 사람만 접근 할수 있도록
    @GetMapping("/add")
    public String like(LikeDTO likeDTO, ReviewDTO reviewDTO, Authentication authentication, RedirectAttributes redirectAttributes){
        log.info("좋아요 GET 테스트");

        log.info("라이크" + likeDTO);
        log.info("리뷰" + reviewDTO);//필요 없지만 테스트용으로 남김(고지훈)

        int facility_no = reviewDTO.getFacility_no();
        String type = reviewDTO.getType();

        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO)authentication.getPrincipal();

        int testMno = memberSecurityDTO.getMno();
        log.info("확인 해야할 mno : " + testMno);

        int rno = likeDTO.getRno();
        int mno = likeDTO.getMno();

        //LikeDTO likeDTO1 = likeService.setOne(rno, mno); 오류 원인 인 놈
        List<LikeDTO> likeDTOS = likeService.selectAll(testMno);

        log.info("라이크 목록 : " + likeDTOS);
        boolean isLiked = false;

        for (LikeDTO existingLike : likeDTOS) {
            int existingRno = existingLike.getRno();

            if (rno == existingRno) {
                // 이미 좋아요를 누른 경우
                isLiked = true;
                likeService.remove(rno, testMno);
                reviewService.like_countDown(rno);
                break;
            }
        }

        if (!isLiked) {
            // 좋아요를 누르지 않은 경우, 좋아요 추가
            likeDTO.setMno(testMno);
            likeService.insert(likeDTO);
            reviewService.like_count(rno);
        }
        log.info("------------------------리다이렉트 시험 좋아요");

        log.info("확인:" + "redirect:/review/read?facility_no="+facility_no+"&type="+type);

        String url =  "redirect:/review/read";
        redirectAttributes.addAttribute("facility_no", facility_no);
        redirectAttributes.addAttribute("type", type);

        return url;
    }
}
