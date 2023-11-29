package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.*;
import com.example.smartparkpj.security.dto.MemberSecurityDTO;
import com.example.smartparkpj.service.*;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.function.BinaryOperator;

@Controller
@RequestMapping("/review")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final MarkerService markerService;

    private final OrderService orderService;//티켓 의 리뷰 작성여부 요효성 검사용

    private final EnterService enterService;

    private final LikeService likeService;

    @GetMapping("/read")
    public void readGet(PageRequestDTO pageRequestDTO, MarkerDTO markerDTO, BindingResult bindingResult, Model model
                            ){
        log.info("review Get !!!!");

        String type = markerDTO.getType();
        int facility_no = markerDTO.getFacility_no();

        pageRequestDTO.setType(type);
        pageRequestDTO.setFacility_no(facility_no);

        log.info("시설 타입 : " + type);
        log.info("시설 넘버 : " + facility_no);

        log.info("pageRequestDTO" + reviewService.getList(pageRequestDTO));

        log.info("review 별점 평균 : " + reviewService.reviewScore(facility_no, type));//별점 평균 점수
        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }


        model.addAttribute("markerDTO", markerDTO);
        model.addAttribute("responseDTO", reviewService.getList(pageRequestDTO));
        model.addAttribute("reviewScore", reviewService.reviewScore(facility_no, type));//평균점수
        model.addAttribute("facilityDTO",enterService.getMarkerOne(type,facility_no));
    }



    @GetMapping("/list")
    public void listGet(Model model){
        log.info("list GET!!");

        model.addAttribute("markerDTO", markerService.getAll());
    }

    @PreAuthorize("isAuthenticated()")//로그인 한 사람만 접근 할수 있도록
    @GetMapping("/add")
    public void add(MarkerDTO markerDTO){
        log.info("review add GET");

        log.info("마커 확인용 로그 : " + markerDTO.getType());
        log.info("마커 확인용 로그 : " + markerDTO.getFacility_no());

    }

    @PostMapping("/add")
    public String add(ReviewDTO reviewDTO, MemberDTO memberDTO, ReviewImageDTO reviewImageDTO){
        log.info("리뷰 컨트롤러 처리창 POST!!!!!!!!!");

        log.info("memberDTO PostMapping mno: " + memberDTO.getEmail_id());
        log.info("reviewDTO PostMapping : " + reviewDTO);

        List<OrderDTO> orderDTOS = orderService.getOneAll(memberDTO.getEmail_id());
        List<ReviewDTO> reviewDTOS = reviewService.getAll();
//       log.info("리뷰 전체 목록 리스트 : " + reviewDTOS);
//       log.info("이용자의 구매한 티켓 목록 : " + orderDTOS);

        // has_ability가 1인 것이 하나도 없을 경우 리다이렉트
        if (orderDTOS.stream().noneMatch(orderDTO -> orderDTO.getHas_ability() == 1)) {
            log.info("리뷰 필터 기능");
            return "redirect:/review/reviewGuide";
        }

        MemberDTO memberDTO1 = reviewService.setOne(memberDTO.getEmail_id());
        int mno = memberDTO1.getMno();

        reviewDTO.setMno(mno);

//        // 리뷰 전체 목록 중 같은 시설에 같은 작성자가 리뷰를 남길 경우
        if (reviewDTOS.stream().anyMatch(existingReview ->
                existingReview.getMno() == reviewDTO.getMno() &&
                        existingReview.getFacility_no() == reviewDTO.getFacility_no() &&
                        existingReview.getType().equals(reviewDTO.getType()))) {
            log.info("같은 시설에 같은 작성자가 이미 리뷰를 남긴 경우");
            return "redirect:/review/reviewGuide2";
        }

//        MemberDTO memberDTO1 = reviewService.setOne(memberDTO.getEmail_id());
//        int mno = memberDTO1.getMno();
//
//        reviewDTO.setMno(mno);

        reviewService.insert(reviewDTO);

        return "redirect:/enter/map";
    }

    //-------------------------------------------------------------------

    @GetMapping("/delete")
    public String delete(ReviewDTO reviewDTO){
        int rno = reviewDTO.getRno();
        reviewService.delete(rno);

        return "redirect:/enter/map";
    }

    @GetMapping("/reviewGuide")
    public void reviewGuide(){
        log.info("reviewGuide 창 연결 테스트");
    }

    @GetMapping("/reviewGuide2")
    public void reviewGuide2(){
        log.info("reviewGuide2 창 연결 테스트");
    }
}
