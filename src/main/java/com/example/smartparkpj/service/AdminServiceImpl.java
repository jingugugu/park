package com.example.smartparkpj.service;


import com.example.smartparkpj.domain.*;
import com.example.smartparkpj.dto.AttractionDTO;
import com.example.smartparkpj.dto.ConvenienceDTO;
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
    public int addAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO) {
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

        return fno;
    }

    @Override
    public int addShop(ShopDTO shopDTO, MarkerDTO markerDTO) {
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

        return fno;
    }

    @Override
    public int addConvenience(ConvenienceDTO convenienceDTO, MarkerDTO markerDTO) {
        int fno = 0;
        ConvenienceVO convenienceVO = modelMapper.map(convenienceDTO, ConvenienceVO.class);
        convenienceMapper.addConvenience(convenienceVO); // 매장 추가
        int cno = convenienceVO.getCno(); // 등록한 편의시설 번호
        fno = cno;
        markerDTO.setFacility_no(fno); // 등록한 시설 번호
        MarkerVO markerVO = modelMapper.map(markerDTO,MarkerVO.class);
        markerMapper.addMarker(markerVO);

        return fno;
    }

    @Override
    public void editAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO) {
        MarkerVO markerVO = modelMapper.map(markerDTO,MarkerVO.class);
        AttractionVO attractionVO = modelMapper.map(attractionDTO, AttractionVO.class);

        log.info("editAttractionService!!!! ----" +attractionDTO.getAno());
        markerMapper.editMarker(markerVO); // 마커 수정
        attractionMapper.editAttraction(attractionVO); // 어트랙션 수정
        attractionMapper.removeTags(attractionDTO.getAno()); // 태그 비우기
        attractionMapper.removeImages(attractionDTO.getAno()); // 이미지 비우기

        if(attractionDTO.getFileNames() != null) { // 새로 이미지 추가
            int ordCnt = 1;
            for (String fileName : attractionDTO.getFileNames()) {
                String[] arr = fileName.split("_");
                AttractionImageVO attractionImageVO = AttractionImageVO.builder()
                        .uuid(arr[0])
                        .fileName(arr[1])
                        .ord(ordCnt++)
                        .ano(attractionDTO.getAno())
                        .build();
                attractionMapper.addAttractionImage(attractionImageVO);
            }
        }
        if(attractionDTO.getTagNames() != null){ // 새로 태그 추가
            for(String tagName : attractionDTO.getTagNames()){
                AttractionTagVO attractionTagVO = AttractionTagVO.builder()
                        .atag_name(tagName)
                        .ano(attractionDTO.getAno())
                        .build();
                attractionMapper.addAttractionTag(attractionTagVO);
            }
        }
    }

    @Override
    public void editShop(ShopDTO shopDTO, MarkerDTO markerDTO) {
        MarkerVO markerVO = modelMapper.map(markerDTO,MarkerVO.class);
        ShopVO shopVO = modelMapper.map(shopDTO, ShopVO.class);

        log.info("editShopService!!!! ----" +shopDTO.getSno());
        markerMapper.editMarker(markerVO); // 마커 수정
        shopMapper.editShop(shopVO); // 어트랙션 수정
        shopMapper.removeImages(shopDTO.getSno()); // 이미지 비우기

        if(shopDTO.getFileNames() != null) { // 새로 이미지 추가
            int ordCnt = 1;
            for (String fileName : shopDTO.getFileNames()) {
                String[] arr = fileName.split("_");
                ShopImageVO shopImageVO = ShopImageVO.builder()
                        .uuid(arr[0])
                        .fileName(arr[1])
                        .ord(ordCnt++)
                        .sno(shopDTO.getSno())
                        .build();
                shopMapper.addShopImage(shopImageVO);
            }
        }

    }

    @Override
    public void editConvenience(ConvenienceDTO convenienceDTO, MarkerDTO markerDTO) {
        MarkerVO markerVO = modelMapper.map(markerDTO,MarkerVO.class);
        ConvenienceVO convenienceVO = modelMapper.map(convenienceDTO, ConvenienceVO.class);

        log.info("editConService!!!! ----" + convenienceDTO.getCno());
        markerMapper.editMarker(markerVO); // 마커 수정
        convenienceMapper.editConvenience(convenienceVO); // 편의시설 수정
    }

    @Override
    public void removeAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO) {

        markerMapper.removeMarker(markerDTO.getMarker_no()); // 마커 삭제
        attractionMapper.removeTags(attractionDTO.getAno()); // 태그 비우기
        attractionMapper.removeImages(attractionDTO.getAno()); // 이미지 비우기
        attractionMapper.removeAttraction(attractionDTO.getAno()); // 어트랙션 삭제
    }

    @Override
    public void removeShop(ShopDTO shopDTO, MarkerDTO markerDTO) {
        markerMapper.removeMarker(markerDTO.getMarker_no()); // 마커 삭제
        shopMapper.removeImages(shopDTO.getSno());
        shopMapper.removeShop(shopDTO.getSno());
    }

    @Override
    public void removeConvenience(ConvenienceDTO convenienceDTO, MarkerDTO markerDTO) {
        markerMapper.removeMarker(markerDTO.getMarker_no()); // 마커 삭제
        convenienceMapper.removeConvenience(convenienceDTO.getCno());
    }
}
