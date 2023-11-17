package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.*;
import com.example.smartparkpj.service.EnterService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enter")
@Log4j2
@RequiredArgsConstructor
public class EnterController {
    private final EnterService enterService;

    @ApiOperation(value = "map Get", notes = "GET 방식으로 마커 목록 획득")
    @GetMapping(value = "/map/list")
    public List<MarkerDTO> get(){
        List<MarkerDTO> dtoList = enterService.getMarkerList();
        return dtoList;
    }

    @GetMapping(value = "/map/{type}/{facility_no}")
    public Object getOne(@PathVariable("type") String type, @PathVariable("facility_no") int facility_no){
        Object object = enterService.getMarkerOne(type, facility_no);
        log.info(object instanceof AttractionDTO);
        log.info(object instanceof ShopDTO);
        log.info(object instanceof ConvenienceDTO);
        return object;
    }

    @ApiOperation(value = "map POST", notes = "POST 방식으로 시설 등록")
    @PostMapping(value = "/map", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> add(@Valid @RequestBody FacilityDTO facilityDTO){

        Map<String, Integer> resultMap = new HashMap<>();
        int marker_no = enterService.add(facilityDTO);

        resultMap.put("marker_no",marker_no);
        return resultMap;
    }
}
