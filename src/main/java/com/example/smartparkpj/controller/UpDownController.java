package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.upload.UploadFileDTO;
import com.example.smartparkpj.dto.upload.UploadResultDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@Log4j2
public class UpDownController {
    @Value("${com.example.upload.basePath}")
    private String uploadBasePath;
    @Value("${com.example.upload.tempPath}")
    private String uploadPath;
    @Value("${com.example.upload.AttractionPath}")
    private String uploadAttractionPath;
    @Value("${com.example.upload.ShopPath}")
    private String uploadShopPath;
    @Value("${com.example.upload.ConveniencePath}")
    private String uploadConveniencePath;

    @PostConstruct
    public void init(){
        File tempFolder01 = new File(uploadBasePath);
        File tempFolder02 = new File(uploadPath);
        File tempFolder03 = new File(uploadAttractionPath);
        File tempFolder04 = new File(uploadShopPath);
        File tempFolder05 = new File(uploadConveniencePath);

        if(!tempFolder01.exists()){
            tempFolder01.mkdirs();
        }
        if(!tempFolder02.exists()){
            tempFolder02.mkdirs();
        }
        if(!tempFolder03.exists()){
            tempFolder03.mkdirs();
        }
        if(!tempFolder04.exists()){
            tempFolder04.mkdirs();
        }
        if(!tempFolder05.exists()){
            tempFolder05.mkdirs();
        }

        uploadBasePath = tempFolder01.getAbsolutePath();
        uploadPath = tempFolder02.getAbsolutePath();
        uploadAttractionPath = tempFolder03.getAbsolutePath();
        uploadShopPath = tempFolder04.getAbsolutePath();
        uploadConveniencePath = tempFolder05.getAbsolutePath();

        log.info("---------------");
        log.info(uploadPath);
    }

    @ApiOperation(value = "Upload Post", notes = "POST 방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO){ // dto 로 이미지 파일을 받음
        log.info("-------------upload post");
        log.info(uploadFileDTO);
        if(uploadFileDTO.getFiles() != null) { // dto 에 파일이 있다면
            final List<UploadResultDTO> list = new ArrayList<>(); // UploadResultDTO 리스트를 만들고
            for(MultipartFile multipartFile : uploadFileDTO.getFiles()) { // 반복문을 돌려 만약 파일을 여러개 받았으면 하나하나 multipartFile 에 저장
                String originalName = multipartFile.getOriginalFilename(); // 파일의 이름을 originalName 에 저장
                log.info(multipartFile.getOriginalFilename());
                String uuid = UUID.randomUUID().toString(); // 파일 이름의 중복 저장을 방지하기 위해 uuid 생성
                Path savePath = Paths.get(uploadPath, uuid +"_" + originalName); // uuid + 파일 이름으로 폴더에 저장
                boolean isImage = false;
                try{
                    multipartFile.transferTo(savePath); // 실제 파일 저장

                    // 이미지 파일이면 섬네일 생성
                    if (Files.probeContentType(savePath).startsWith("image")){
                        log.info(Files.probeContentType(savePath));
                        isImage = true;
                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }
                } catch (IOException e){
                    throw new RuntimeException(e);
                }
                list.add(UploadResultDTO.builder()
                        .uuid(uuid)
                        .fileName(originalName)
                        .img(isImage)
                        .build());
            }
            return list;
        }
        return null;
    }

    @ApiOperation(value = "View 파일", notes = "GET방식으로 시설 이미지 조회")
    @GetMapping(value = "/view/{fileName}/{type}/{folderName}")
    public ResponseEntity<Resource> viewFileSet(@PathVariable String fileName, @PathVariable String type, @PathVariable String folderName){

        String realType = "";
        if(type.equals("어트랙션")){
            realType = "attraction";
            folderName = "A" + folderName;
        }
        else if(type.equals("매장")){
            realType = "shop";
            folderName = "S" + folderName;

        }
        else{
            realType = "convenience";
            folderName = "C" + folderName;

        }
        String resultPath = uploadBasePath + File.separator + realType + File.separator + folderName + File.separator + fileName;
        Resource resource = new FileSystemResource(resultPath);

        String resourceName = resource.getFilename();
        log.info("view file ================" + resourceName);
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }
    @ApiOperation(value = "View 파일", notes = "GET방식으로 첨부파일 조회")
    @GetMapping(value = "/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();
        log.info("view file ================" + resourceName);
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @ApiOperation(value = "remove 파일", notes = "DELETE 방식으로 파일 삭제")
    @DeleteMapping(value = "/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName){
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();
        log.info("filename = " +resourceName);
        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;
        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete(); // 이미지 삭제

            log.info("removed : " + resource.getFile());
            if(contentType.startsWith("image")){ // 섬네일이 존재한다면
                File thumbFile = new File(uploadPath + File.separator + "s_" + fileName);
                thumbFile.delete(); // 섬네일 삭제
            }
        } catch (IOException e){
            log.error(e.getMessage());
        }
        resultMap.put("result", removed);
        return resultMap;
    }

    @ApiOperation(value = "remove default 파일", notes = "DELETE 방식으로 파일 삭제")
    @DeleteMapping(value = "/remove/{fileName}/{type}/{folderName}")
    public Map<String, Boolean> removeDefaultFile(@PathVariable String fileName, @PathVariable String type, @PathVariable String folderName){
        log.info("remove default value ======= " + fileName, type,folderName);
        String deletePath = uploadPath;

        if(type.equals("어트랙션")){
            deletePath = uploadAttractionPath;
        }
        else if(type.equals("매장")){
            deletePath = uploadShopPath;
        }
        else if(type.equals("편의시설")){
            deletePath = uploadConveniencePath;
        }

        log.info("remove default value file path ======= " + deletePath);
        Resource resource = new FileSystemResource(deletePath + File.separator + folderName + File.separator + fileName);

        String resourceName = resource.getFilename();
        log.info("filename = " +resourceName);
        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;
        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete(); // 이미지 삭제

            log.info("removed : " + resource.getFile());
            if(contentType.startsWith("image")){
                File thumbFile = new File(deletePath + File.separator + folderName + File.separator + "s_" + fileName);
                thumbFile.delete(); // 섬네일 삭제
            }
        } catch (IOException e){
            log.error(e.getMessage());
        }
        resultMap.put("result", removed);
        return resultMap;
    }

}