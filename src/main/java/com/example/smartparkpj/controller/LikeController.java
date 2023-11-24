package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.LikeDTO;
import com.example.smartparkpj.dto.ReviewDTO;
import com.example.smartparkpj.service.LikeService;
import com.example.smartparkpj.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/like")
@Log4j2
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    private final ReviewService reviewService;

    @PreAuthorize("isAuthenticated()")//로그인 한 사람만 접근 할수 있도록
    @GetMapping("/add")
    public String like(LikeDTO likeDTO, ReviewDTO reviewDTO){
        log.info("좋아요 GET 테스트");

        log.info("라이크" + likeDTO);
        log.info("리뷰" + reviewDTO);//필요 없지만 테스트용으로 남김(고지훈)

        int rno = likeDTO.getRno();
        int mno = likeDTO.getMno();

//        LikeDTO likeDTO1 = likeService.setOne(rno, mno);
//
//        log.info("좋아요 컨트롤러 처리 : " + likeDTO1);
//        if(likeDTO1 == null){
//            //좋아요 down
//            likeService.remove(rno, mno);
//            reviewService.like_countDown(rno);
//        }else {
//            //좋아요 up
//            likeService.insert(likeDTO);
//            reviewService.like_count(rno);
//        }

//        //좋아요 down
//        likeService.remove(rno, mno);
//        reviewService.like_countDown(rno);

        //좋아요 up
        likeService.insert(likeDTO);
        reviewService.like_count(rno);

        return "redirect:/review/list";
    }
}
