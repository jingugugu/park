package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.*;
import com.example.smartparkpj.dto.*;
import com.example.smartparkpj.mapper.AttractionMapper;
import com.example.smartparkpj.mapper.ConvenienceMapper;
import com.example.smartparkpj.mapper.MarkerMapper;
import com.example.smartparkpj.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class EnterServiceImpl implements EnterService {
    private final ModelMapper modelMapper;

    private final AttractionMapper attractionMapper;
    private final ShopMapper shopMapper;
    private final ConvenienceMapper convenienceMapper;

    private final MarkerMapper markerMapper;


    @Override
    public int add(FacilityDTO facilityDTO) {

        int fno = 0;
        if(facilityDTO.getMarkerDTO() != null) { // 마커 타입으로 분류
            if (facilityDTO.getMarkerDTO().getType().equals("어트랙션")) { // 어트랙션
                AttractionDTO attractionDTO = facilityDTO.getAttractionDTO();
                AttractionVO attractionVO = modelMapper.map(attractionDTO,AttractionVO.class);
                attractionMapper.addAttraction(attractionVO); // 어트랙션 추가
                int ano = attractionVO.getAno(); // 등록한 어트랙션 번호
                if(attractionDTO.getFileNames() != null){ // 이미지 추가
                    int ordCnt = 1;
                    for(String fileName : attractionDTO.getFileNames()){
                        String[] arr = fileName.split("_");
                        AttractionImageVO attractionImageVO = AttractionImageVO.builder()
                                .uuid(arr[0])
                                .fileName(arr[1])
                                .ord(ordCnt++)
                                .ano(ano)
                                .build();
                        attractionMapper.addAttractionImage(attractionImageVO);
                    }
                }

                if(attractionDTO.getTagNames() != null){ // 태그 추가
                    for(String tagName : attractionDTO.getTagNames()){
                        AttractionTagVO attractionTagVO = AttractionTagVO.builder()
                                .atag_name(tagName)
                                .ano(ano)
                                .build();
                        attractionMapper.addAttractionTag(attractionTagVO);
                    }
                }
                fno = ano;
            }
            else if (facilityDTO.getMarkerDTO().getType().equals("매장")){ // 매장
                ShopDTO shopDTO = facilityDTO.getShopDTO();
                ShopVO shopVO = modelMapper.map(shopDTO, ShopVO.class);
                shopMapper.addShop(shopVO); // 매장 추가
                int sno = shopVO.getSno(); // 등록한 매장 번호
                if(shopDTO.getFileNames() != null){ // 이미지 추가
                    int ordCnt = 1;
                    for(String fileName : shopDTO.getFileNames()){
                        String[] arr = fileName.split("_");
                        ShopImageVO shopImageVO = ShopImageVO.builder()
                                .uuid(arr[0])
                                .fileName(arr[1])
                                .ord(ordCnt++)
                                .sno(sno)
                                .build();
                        shopMapper.addShopImage(shopImageVO);
                    }
                }
                fno = sno;
            }
            else{ // 기타, 편의시설
                ConvenienceDTO convenienceDTO = facilityDTO.getConvenienceDTO();
                ConvenienceVO convenienceVO = modelMapper.map(convenienceDTO, ConvenienceVO.class);
                convenienceMapper.addConvenience(convenienceVO);
                int cno = convenienceVO.getCno();

                fno = cno;
            }
        }

        MarkerDTO markerDTO = facilityDTO.getMarkerDTO();
        markerDTO.setFacility_no(fno); // 등록한 시설 번호
        MarkerVO markerVO = modelMapper.map(markerDTO,MarkerVO.class);
        markerMapper.addMarker(markerVO);
        return markerVO.getMarker_no();
    }

    @Override
    public List<MarkerDTO> getMarkerList() { // 마커 목록 받기
        List<MarkerVO> voList = markerMapper.getMarkerList();
        List<MarkerDTO> dtoList = new ArrayList<>();
        if(voList != null){
            for(MarkerVO markerVO : voList){
                MarkerDTO markerDTO = modelMapper.map(markerVO, MarkerDTO.class);
                dtoList.add(markerDTO);
            }
        }
        return dtoList;
    }

    @Override
    public Object getMarkerOne(String type, int facility_no) {

        if(type != null){
            if(type.equals("어트랙션")){
                AttractionVO attractionVO = attractionMapper.getOne(facility_no);
                AttractionDTO attractionDTO = modelMapper.map(attractionVO,AttractionDTO.class);
                return attractionDTO;
            }
            else if(type.equals("매장")){
                ShopVO shopVO = shopMapper.getOne(facility_no);
                ShopDTO shopDTO = modelMapper.map(shopVO, ShopDTO.class);
                return shopDTO;
            }
            else{
                ConvenienceVO convenienceVO = convenienceMapper.getOne(facility_no);
                ConvenienceDTO convenienceDTO = modelMapper.map(convenienceVO, ConvenienceDTO.class);
                return convenienceDTO;
            }
        }
        return null;
    }

}
