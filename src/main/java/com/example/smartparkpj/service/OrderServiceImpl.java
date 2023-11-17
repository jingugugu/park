package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.OrderVO;
import com.example.smartparkpj.dto.OrderDTO;
import com.example.smartparkpj.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final ModelMapper modelMapperConfig;

    private final OrderMapper orderMapper;

    @Override
    public void add(OrderDTO orderDTO) {

        //전달 받은 날짜 를 벡엔드 에서 데이터 추가 처리
        Date date = new Date();
        String str = orderDTO.getStartDate();
        log.info("방문 시간 :" + str);
        String strTime = null;
        String dateString = null;
        int dateStringEnd = 0;
        int endTime = 7;

        if(orderDTO.getTno() == 1){dateString = "12:00:00";
            dateStringEnd = 6;
        }else if(orderDTO.getTno() == 2){dateString = "18:00:00";
            dateStringEnd = 4;
        }else if(orderDTO.getTno() == 3){dateString = "12:00:00";
            dateStringEnd = 10;
        }else if(orderDTO.getTno() == 4){dateString = "12:00:00";
            dateStringEnd = 10;
            endTime = 30;
        }
        // DateTimeFormatter를 사용하여 원하는 형식 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startDateTime = LocalDateTime.parse(str + " " + dateString, formatter);

        // 변환된 LocalDateTime 출력
        log.info("시작 시간 :" + startDateTime);

        LocalDateTime endDate = startDateTime.plusDays(endTime).plusHours(dateStringEnd);
        log.info("끝 시간 :" + endDate);


        //티켓 만료 일자, 리뷰 유효일자
        int finishedChek = 1;
        int has_abilityChek = 1;

        //티켓 구매 코드
        String ticketCode = UUID.randomUUID().toString();

        OrderVO orderVO = OrderVO.builder()
                .orderCode(ticketCode)
                .tno(orderDTO.getTno())
                .email_id(orderDTO.getEmail_id())
                .orderDate(date)
                .startDate(startDateTime)
                .endDate(endDate)
                .has_ability(finishedChek)
                .finished(has_abilityChek)
                .price(orderDTO.getPrice()*orderDTO.getPeople_count())
                .people_count(orderDTO.getPeople_count())
                .build();
        orderMapper.insert(orderVO);
    }
}
