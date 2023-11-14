package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class MapServiceTests {
    @Autowired
    private MapService mapService;

    @Test
    public void getMarkerListTest(){
        List<MarkerDTO> dtoList = mapService.getMarkerList();
        log.info(dtoList);
    }
    @Test
    public void addMarkerTest(){ // 마커 추가 테스트
        AttractionDTO attractionDTO = AttractionDTO.builder()
                .aname("롤러코스터5")
                .ainfo("재밌음")
                .ainfo_detail("재밌음굿")
                .passengersCount(5)
                .build();

        ShopDTO shopDTO = ShopDTO.builder()
                .shop_menu("메뉴1, 메뉴2, 메뉴3")
                .shop_name("매장3")
                .shop_guide("라스트오더 어쩌구")
                .shop_tel("02-112-4444")
                .shop_time("평일됨")
                .shop_price("6천원~10만원")
                .build();

        ConvenienceDTO convenienceDTO = ConvenienceDTO.builder()
                .name("편의시설2")
                .con_guide("편의시설개요")
                .con_info("편의내용")
                .con_tel("02-333-5555")
                .con_img("편의아이콘")
                .build();

        List<String> fileNames = new ArrayList<>();
        fileNames.add("1348deba-e89e-486d-a9e8-bae68e4b660b_renoir04.png");
        fileNames.add("1348deba-e55e-486d-a9e8-bae68e4b660b_renoir02.png");
        fileNames.add("444417ba-e55e-486d-a9e8-bae68e4b660b_renoir03.png");

        List<String> tagNames = new ArrayList<>();
        tagNames.add("태그1");
        tagNames.add("태그2");
        tagNames.add("태그3");

        attractionDTO.setFileNames(fileNames);
        attractionDTO.setTagNames(tagNames);

        shopDTO.setFileNames(fileNames);

        MarkerDTO markerDTO = MarkerDTO.builder()
                .latitude(33.461587465874)
                .longitude(124.853054479778)
                .type("편의시설") // 해당 타입으로 등록할 시설 설정
                .marker_img("마커아이콘2")
                .build();

        FacilityDTO facilityDTO = new FacilityDTO();
        facilityDTO.setAttractionDTO(attractionDTO);
        facilityDTO.setShopDTO(shopDTO);
        facilityDTO.setConvenienceDTO(convenienceDTO);
        facilityDTO.setMarkerDTO(markerDTO);

        mapService.add(facilityDTO);

    }
}
