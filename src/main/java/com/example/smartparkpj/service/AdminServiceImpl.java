package com.example.smartparkpj.service;


import com.example.smartparkpj.domain.*;
import com.example.smartparkpj.dto.AttractionDTO;
import com.example.smartparkpj.dto.MarkerDTO;
import com.example.smartparkpj.dto.ShopDTO;
import com.example.smartparkpj.mapper.AttractionMapper;
import com.example.smartparkpj.mapper.ConvenienceMapper;
import com.example.smartparkpj.mapper.MarkerMapper;
import com.example.smartparkpj.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final ModelMapper modelMapper;

    private final AttractionMapper attractionMapper;
    private final ShopMapper shopMapper;
    private final ConvenienceMapper convenienceMapper;

    private final MarkerMapper markerMapper;
    @Override
    public void addAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO) {
        int fno = 0;
        AttractionVO attractionVO = modelMapper.map(attractionDTO,AttractionVO.class);
        attractionMapper.addAttraction(attractionVO); // 어트랙션 추가
        int ano = attractionVO.getAno(); // 등록한 어트랙션 번호
        if(attractionDTO.getFileNames() != null) { // 이미지 추가
            int ordCnt = 1;
            for (String fileName : attractionDTO.getFileNames()) {
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

        markerDTO.setFacility_no(fno); // 등록한 시설 번호
        MarkerVO markerVO = modelMapper.map(markerDTO,MarkerVO.class);
        markerMapper.addMarker(markerVO);
    }

    @Override
    public void addShop(ShopDTO shopDTO, MarkerDTO markerDTO) {
        int fno = 0;
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
        markerDTO.setFacility_no(fno); // 등록한 시설 번호
        MarkerVO markerVO = modelMapper.map(markerDTO,MarkerVO.class);
        markerMapper.addMarker(markerVO);
    }
}
