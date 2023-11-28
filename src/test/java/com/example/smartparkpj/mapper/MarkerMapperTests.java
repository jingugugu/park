package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.AttractionImageVO;
import com.example.smartparkpj.domain.AttractionTagVO;
import com.example.smartparkpj.domain.AttractionVO;
import com.example.smartparkpj.domain.MarkerVO;
import com.example.smartparkpj.mapper.AttractionMapper;
import com.example.smartparkpj.mapper.MarkerMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class MarkerMapperTests {
    @Autowired
    private MarkerMapper markerMapper;
    @Autowired
    private AttractionMapper attractionMapper;

    @Test
    public void getMarkerList(){
        List<MarkerVO> markerVOList = markerMapper.getMarkerList();

        log.info(markerVOList);
    }
    @Test
    public void addMarkerTest(){
        AttractionVO attractionVO = AttractionVO.builder()
                .aname("롤러코스터5")
                .ainfo("재밌는 롤러코스터")
                .ainfo_detail("6~18세 탑승가능")
                .passengersCount(4)
                .build();
        attractionMapper.addAttraction(attractionVO);

        for(int i = 1; i <= 3 ; i++){
            AttractionImageVO attractionImageVO = AttractionImageVO.builder()
                    .uuid(UUID.randomUUID().toString())
                    .fileName("이미지" + i + ".jpg")
                    .ord(i)
                    .ano(attractionVO.getAno())
                    .build();

            attractionMapper.addAttractionImage(attractionImageVO);
        }

        for(int i = 1; i <= 3 ; i++){
            AttractionTagVO attractionTagVO = AttractionTagVO.builder()
                    .atag_name("태그" + i)
                    .ano(attractionVO.getAno())
                    .build();

            attractionMapper.addAttractionTag(attractionTagVO);
        }

        Random random = new Random();
        String randNum = "";
        for(int i = 0; i < 12; i++){
                // 마지막 자리에서는 0을 포함하지 않도록 설정
                if (i == 11) {
                    randNum += random.nextInt(9) + 1;
                } else {
                    randNum += random.nextInt(10);
                }
            }
        String latNum = "33.46" + randNum;

        randNum = "";

        for(int i = 0; i < 12; i++){
            // 마지막 자리에서는 0을 포함하지 않도록 설정
            if (i == 11) {
                randNum += random.nextInt(9) + 1;
            } else {
                randNum += random.nextInt(10);
            }
        }

        String longNum = "124.85" + randNum;

        MarkerVO markerVO = MarkerVO.builder()
                .latitude(Double.parseDouble(latNum))
                .longitude(Double.parseDouble(longNum))
                .type("어트랙션")
                .facility_no(attractionVO.getAno())
                .build();

        markerMapper.addMarker(markerVO);
    }
}
