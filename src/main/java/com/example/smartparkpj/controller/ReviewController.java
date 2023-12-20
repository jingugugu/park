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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private final MemberService memberService;


    @GetMapping("/read")
    public void readGet(Authentication authentication, PageRequestDTO pageRequestDTO, MarkerDTO markerDTO, BindingResult bindingResult, Model model){
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
        if(authentication != null) {
            MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO)authentication.getPrincipal();
            model.addAttribute("orderDTOList", reviewService.getAvailableOrderList(memberSecurityDTO.getMno(), type, facility_no));
        }

    }

    @PreAuthorize("isAuthenticated()")//로그인 한 사람만 접근 할수 있도록
    @PostMapping("/read")
    public String readPost(LikeDTO likeDTO, ReviewDTO reviewDTO, Authentication authentication, RedirectAttributes redirectAttributes){
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
    public String add(ReviewDTO reviewDTO, MemberDTO memberDTO, ReviewImageDTO reviewImageDTO, RedirectAttributes redirectAttributes,
                      Authentication authentication){
        log.info("리뷰 컨트롤러 처리창 POST!!!!!!!!!");

        log.info("memberDTO PostMapping mno: " + memberDTO.getEmail_id());
        log.info("reviewDTO PostMapping : " + reviewDTO);

        int facility_no = reviewDTO.getFacility_no();
        String type = reviewDTO.getType();

        List<OrderDTO> orderDTOS = orderService.getOneAll(memberDTO.getEmail_id());
        List<ReviewDTO> reviewDTOS = reviewService.getAll();
        //OrderDTO orderDTOOno = orderService.getOneNow(memberDTO.getEmail_id());//작성자 티켓 중 가장 최근 번호 구하는 용
//       log.info("리뷰 전체 목록 리스트 : " + reviewDTOS);
//       log.info("이용자의 구매한 티켓 목록 : " + orderDTOS);

        // 작성하지 않은 ono 값을 찾기
//        int maxOno = orderDTOS.stream()
//                .mapToInt(OrderDTO::getOno)
//                .max()
//                .orElse(0); // 만약 orderDTOS가 비어있다면 0을 반환합니다.
//        log.info("작성하지 않은 ono" + maxOno);
//
//        reviewDTO.setOno(maxOno);

        // has_ability가 1인 것이 하나도 없을 경우 리다이렉트
        if (orderDTOS.stream().noneMatch(orderDTO -> orderDTO.getHas_ability() == 1)) {
            log.info("리뷰 필터 기능");
            return "redirect:/review/reviewGuide";
        }

        MemberDTO memberDTO1 = memberService.getMember(memberDTO.getEmail_id());
        int mno = memberDTO1.getMno();

        reviewDTO.setMno(mno);

//        for (ReviewDTO reviewDTO1 : reviewDTOS) {
//            int reviewOno = reviewDTO1.getOno();
//
//            if (reviewOno == maxOno && reviewDTO1.getFacility_no() == reviewDTO.getFacility_no()) {
//                log.info("reviewDTOS에서 maxOno와 일치하는 ono 값을 찾았습니다.");
//                return "redirect:/review/reviewGuide2";
//            }
//        }

// 리뷰 전체 목록 중 같은 시설에 같은 작성자가 이미 리뷰를 남긴 경우
//        if (reviewDTOS.stream().anyMatch(existingReview ->
//                existingReview.getMno() == reviewDTO.getMno() &&
//                        existingReview.getFacility_no() == reviewDTO.getFacility_no() &&
//                        existingReview.getType().equals(reviewDTO.getType())) &&
//                maxOno != reviewDTO.getOno()) {
//            log.info("같은 시설에 같은 작성자가 이미 리뷰를 남긴 경우이지만 maxOno가 다르므로 조건에 해당하지 않습니다.");
//            return "redirect:/review/reviewGuide";
//        }

        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO)authentication.getPrincipal();
        String email_id = memberSecurityDTO.getEmail_id();//여기 작업중이였음 11/32


//        MemberDTO memberDTO1 = reviewService.setOne(memberDTO.getEmail_id());
//        int mno = memberDTO1.getMno();
//
//        reviewDTO.setMno(mno);

        reviewService.insert(reviewDTO);

        log.info("확인:" + "redirect:/review/read?facility_no="+facility_no+"&type="+type);

        String url =  "redirect:/review/read";
        redirectAttributes.addAttribute("facility_no", facility_no);
        redirectAttributes.addAttribute("type", type);

        return url;
    }

    //-------------------------------------------------------------------

    @GetMapping("/delete")
    public String delete(ReviewDTO reviewDTO, RedirectAttributes redirectAttributes){
        int rno = reviewDTO.getRno();
        reviewDTO = reviewService.delete(rno);
        redirectAttributes.addAttribute("facility_no", reviewDTO.getFacility_no());
        redirectAttributes.addAttribute("type", reviewDTO.getType());
        return "redirect:/review/read";
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
