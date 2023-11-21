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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@Log4j2
public class UpDownController {
    @Value("${com.example.upload.tempPath}")
    private String uploadPath;

    @ApiOperation(value = "Upload Post", notes = "POST 방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO){
        log.info("-------------upload post");
        log.info(uploadFileDTO);
        if(uploadFileDTO.getFiles() != null){
            List<UploadResultDTO> list = new ArrayList<>();
            for(MultipartFile multipartFile : uploadFileDTO.getFiles()){
                String originalName = multipartFile.getOriginalFilename();
                log.info(multipartFile.getOriginalFilename());
                String uuid = UUID.randomUUID().toString();

                Path savePath = Paths.get(uploadPath, uuid +"_" + originalName);
                boolean isImage = false;
                try{
                    multipartFile.transferTo(savePath);

//                    // 이미지 파일이면 섬네일 생성
//                    if (Files.probeContentType(savePath).startsWith("image")){
//                        log.info(Files.probeContentType(savePath));
//                        isImage = true;
//                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName);
//                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
//                    }
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

    @ApiOperation(value = "View 파일", notes = "GET방식으로 첨부파일 조회")
    @GetMapping(value = "/view/{fileName}")
    public ResponseEntity<Resource> viewFileSet(@PathVariable String fileName){
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
//            if(contentType.startsWith("image")){
//                File thumbFile = new File(uploadPath + File.separator + "s_" + fileName);
//                thumbFile.delete(); // 섬네일 삭제
//            }
        } catch (IOException e){
            log.error(e.getMessage());
        }
        resultMap.put("result", removed);
        return resultMap;
    }
}