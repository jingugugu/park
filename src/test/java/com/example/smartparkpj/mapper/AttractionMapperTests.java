package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.AttractionImageVO;
import com.example.smartparkpj.domain.AttractionTagVO;
import com.example.smartparkpj.domain.AttractionVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Log4j2
public class AttractionMapperTests {
    @Autowired
    private AttractionMapper attractionMapper;

    @Test
    public void addAttractionTest(){     // 어트랙션 추가 테스트
        AttractionVO attractionVO = AttractionVO.builder()
                .aname("롤러코스터1")
                .ainfo("재밌는 롤러코스터")
                .ainfo_detail("6~18세 탑승가능")
                .passengersCount(4)
                .build();

        attractionMapper.addAttraction(attractionVO);
    }

    @Test
    public void addAttractionImageTest(){     // 이미지 추가 테스트
        AttractionVO attractionVO = AttractionVO.builder()
                .aname("롤러코스터1")
                .ainfo("재밌는 롤러코스터")
                .ainfo_detail("6~18세 탑승가능")
                .passengersCount(4)
                .build();
        attractionMapper.addAttraction(attractionVO);

        for(int i = 1; i <= 3 ; i++){
            AttractionImageVO attractionImageVO = AttractionImageVO.builder()
                    .uuid(UUID.randomUUID().toString())
                    .fileName("이미지" + i)
                    .ord(i)
                    .ano(attractionVO.getAno())
                    .build();

            attractionMapper.addAttractionImage(attractionImageVO);
        }
    }

    @Test
    public void addAttractionTagTest(){ // 태그 추가 테스트
        AttractionVO attractionVO = AttractionVO.builder()
                .aname("롤러코스터3")
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
    }
}
